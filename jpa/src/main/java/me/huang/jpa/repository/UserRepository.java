package me.huang.jpa.repository;

import me.huang.jpa.entity.po.User;
import me.huang.jpa.entity.projection.UsernameAndCity;
import me.huang.jpa.entity.streamable.Usernames;
import me.huang.jpa.entity.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    User findByAddressCity(String city);

    Page<User> findByAddressCity(String city, Pageable pageable);

    User findByUsername(String username);

    List<User> findByAddressCity(String city, Sort sort);

    Usernames findByAddressProvince(String province);

    @Modifying
    @Transactional
    @Query("update User set password = :password where username = :username")
    int updatePasswordByUsername(@Param("password") String password, @Param("username") String username);

    @Query("select id, LENGTH(username) as username_len from User where address.province = :province")
    List<Object[]> findByAddressProvince(@Param("province") String province, Sort sort);

    UsernameAndCity findByPassword(String password);

    UserVo findByPasswordAndPhone(String password, String phone);

    <T> T findByPassword(String password, Class<T> type);
}
