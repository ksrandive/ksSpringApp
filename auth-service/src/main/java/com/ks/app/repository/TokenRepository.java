package com.ks.app.repository;

import com.ks.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @NativeQuery("select * from token t inner join user_credential u on t.refuser_id = u.user_id" +
            " where t.refuser_id = :userId and t.logged_out = false")
    List<Token> findAllAccessTokensByUser(@Param("userId") Integer userId);

    Optional<Token> findByAccessToken(String token);

    Optional<Token > findByRefreshToken(String token);
}
