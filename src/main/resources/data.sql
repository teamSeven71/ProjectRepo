-- UserEntity 테이블 생성
CREATE TABLE IF NOT EXISTS user_entity (
                                           user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           email VARCHAR(255) NOT NULL UNIQUE,
                                           password VARCHAR(255) NOT NULL,
                                           name VARCHAR(255) NOT NULL,
                                           nickname VARCHAR(255) NOT NULL,
                                           role VARCHAR(255) DEFAULT 'USER',
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- CommentEntity 테이블 생성
CREATE TABLE IF NOT EXISTS comment_entity (
                                              comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              user_id BIGINT,
                                              content TEXT,
                                              article_id BIGINT,
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                              FOREIGN KEY (user_id) REFERENCES user_entity(user_id),
                                              FOREIGN KEY (article_id) REFERENCES article_entity(article_id)
);

-- CategoryEntity 테이블 생성
CREATE TABLE IF NOT EXISTS category_entity (
                                               category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               category_name VARCHAR(255)
);

-- ArticleEntity 테이블 생성
CREATE TABLE IF NOT EXISTS article_entity (
                                              article_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              title VARCHAR(255) NOT NULL,
                                              content TEXT NOT NULL,
                                              user_id BIGINT,
                                              deleted_at TIMESTAMP,
                                              good_count BIGINT DEFAULT 0,
                                              bad_count BIGINT DEFAULT 0,
                                              view_count BIGINT DEFAULT 0,
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                              FOREIGN KEY (user_id) REFERENCES user_entity(user_id)
);

-- ArticleCategoryEntity 중간 테이블 생성
CREATE TABLE IF NOT EXISTS article_category_entity (
                                                       article_id BIGINT,
                                                       category_id BIGINT,
                                                       PRIMARY KEY (article_id, category_id),
                                                       FOREIGN KEY (article_id) REFERENCES article_entity(article_id),
                                                       FOREIGN KEY (category_id) REFERENCES category_entity(category_id)
);



-- INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user1', 'p1', 'UserOne', 'NickName1', 'USER');
-- INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user2', 'p2', 'UserTwo', 'NickName2', 'USER');

-- ARTICLE, NOTICE, JAVA, SQL, JS, ETC, PYTHON, DB
-- INSERT INTO category_entity (category_name) VALUES ('ARTICLE'); --1
-- INSERT INTO category_entity (category_name) VALUES ('NOTICE');  --2
-- INSERT INTO category_entity (category_name) VALUES ('JAVA');    --3
-- INSERT INTO category_entity (category_name) VALUES ('SQL');     --4
-- INSERT INTO category_entity (category_name) VALUES ('JS');      --5
-- INSERT INTO category_entity (category_name) VALUES ('ETC');     --6
-- INSERT INTO category_entity (category_name) VALUES ('PYTHON');  --7
-- INSERT INTO category_entity (category_name) VALUES ('DB');      --8

-- article 하나 생성 시 1set
-- INSERT INTO article_entity (title, content, create_At, deleted_At, modified_At, good_Count, bad_Count, view_Count) VALUES ('공지1', '내용1', NOW(),null, NOW(), 0, 0, 0);
-- INSERT INTO article_category_entity (article_id, category_id) VALUES (1, 1);

-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('공지2', '내용2', NOW(), NOW(), 'NOTICE');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('공지3', '내용3', NOW(), NOW(), 'NOTICE');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('공지4', '내용4', NOW(), NOW(), 'NOTICE');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('공지5', '내용5', NOW(), NOW(), 'NOTICE');
--
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('자유1', '내용1', NOW(), NOW(), 'ARTICLE');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('자유2', '내용2', NOW(), NOW(), 'ARTICLE');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('JS1', '내용1', NOW(), NOW(), 'JS');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('SQL1', '내용1', NOW(), NOW(), 'SQL');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('DB1', '내용1', NOW(), NOW(), 'DB');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('JAVA1', '내용1', NOW(), NOW(), 'JAVA');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('PYTHON1', '내용1', NOW(), NOW(), 'PYTHON');
-- INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('ETC1', '내용1', NOW(), NOW(), 'ETC');

/*INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목1', '내용1', NOW(), NOW(), 'ARTICLE');

INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목2', '내용2', NOW(), NOW(), 'ARTICLE');

INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목3', '내용3', NOW(), NOW(), 'ARTICLE');
*/