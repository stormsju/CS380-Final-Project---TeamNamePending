package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    @Query(value = "SELECT f FROM Friend f WHERE f.id.friendToId =?1")
    List<Friend> findByFriendToId(int friendToId);

    @Query(value = "SELECT f FROM Friend f WHERE f.id.friendOfId =?1")
    List<Friend> findByFriendOfId(int friendOfId);
}
