package com.odont.odont.models.dao;

import com.odont.odont.models.entity.CpChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IChatDao extends JpaRepository <CpChatEntity,Integer> {

    // JPQL SELECT ch FROM CpChat ch WHERE ch.cpUserUserId.userId = 1 ORDER BY ch.chatId DESC <<LIMIT 1>>
    @Query(value = "SELECT * FROM cp_chat where  cp_user_user_id = ?1 ORDER BY chat_id DESC LIMIT 1", nativeQuery = true)
    public CpChatEntity findLastChatByUserId(Integer userId);


    @Query(value = "select * from cp_chat where user_id=?1 and conversation_id=?2 and message_id=?3",nativeQuery = true)
    public CpChatEntity findMessageAndConversationByUserId(Integer user_id,Integer conversation,Integer message);

}
