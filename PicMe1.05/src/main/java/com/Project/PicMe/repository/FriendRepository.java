package com.Project.PicMe.repository;

import com.Project.PicMe.compositeKey.FriendId;
import com.Project.PicMe.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

}
