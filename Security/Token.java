package Security;
import java.util.*;
public class Token {
	private long seed, secToken, privateKey, publicKey;
	private int x, y;
	
	/* How it works:
	 * 1) Seed and Private key are stored in database.
	 * 2) Seed generates a Private key via random.
	 * 3) Public Key is generated from the Seed and the Private key.
	 * 4) Public key will be used to query the Private key in DB.
	 * 5) Seed will remain (relatively) unknown to the systems.
	 * Now for the tricky part:
	 * 6) Public key needs to transformed into a token, enter Euclid's Pulverizer
	 * 7) secToken = [Public Key] / [pulv(privateKey,publicKey)]. As long as the seed remains unchanged,
	 * 		the secToken (acts as a base) can be used to determine the public key.
	 * 
	 * DB Stores:
	 * Private Key, Public Key, seed, secToken
	 * 
	 * Local File Stores:
	 * secToken, Public Key
	 */
	
	public Token() {
		this.seed = System.currentTimeMillis();
		Random rand = new Random(this.seed);
		this.privateKey = Math.abs(rand.nextInt());
		this.publicKey = this.seed / this.privateKey;
		this.x = 1;
		this.y = 1;
		this.secToken = Pulv(this.privateKey, this.publicKey, this.x, this.y);
		//set to a "complex" GCD (ensure values are non-prime interactions)
		while(this.secToken <= 1) {
			this.secToken = Pulv(this.privateKey, this.publicKey, this.x, this.y);
		}
		//burn a rand after each use
		rand.nextInt();
	}
	
	private int Pulv(long priKey, long pubKey, int x, int y) {
		//base case, perfect divisor found
		if (priKey == 0) {
			this.x = 0;
			this.y = 1;
			return (int)pubKey;
		}
		//prep recursive checks
		//reset linear combination for each pass
		int xPrime = 1, yPrime = 1;		
		int pulv = Pulv((int)pubKey % (int)priKey, priKey, xPrime, yPrime);
		//once base case found, 
		//x = y' - (current multiple) * x'
		//y = x'
		this.x = yPrime - (int)(pubKey / priKey) * xPrime;
		this.y = xPrime;
		
		return pulv;
	}
	
	
	
	//test/build methods not to be included in final implementation
	public long getToken() {
		return this.secToken;
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public long getPrivateKey() {
		return this.privateKey;
	}
	
	public long getPublicKey() {
		return this.publicKey;
	}
	
	public int returnX() {
		return this.x;
	}
	
	public int returnY() {
		return this.y;
	}

	public static void main(String[] args) {
		Token t = new Token();
		
		// public key is derived by the private key, which represents the nextInt() in a random seed.
		//(token x public key) should be the same on both ends of authentication to pass
		System.out.println("Seed: " + t.getSeed());
		System.out.println("Private Key held in database: " + t.getPrivateKey());
		System.out.println("Public Key built from private key: " + t.getPublicKey());
		System.out.println("Token base: " + t.getToken());
		System.out.println("Token handled by user: " + t.getToken() * t.getPublicKey());
	}
}