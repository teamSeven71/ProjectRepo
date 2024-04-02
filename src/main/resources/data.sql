INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user1', 'p1', 'UserOne', 'NickName1', 'USER');
INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user2', 'p2', 'UserTwo', 'NickName2', 'USER');

-- ARTICLE, NOTICE, JAVA, SQL, JS, ETC, PYTHON, DB
INSERT INTO category_entity (category_name) VALUES ('ARTICLE'); --1
INSERT INTO category_entity (category_name) VALUES ('NOTICE');  --2
INSERT INTO category_entity (category_name) VALUES ('JAVA');    --3
INSERT INTO category_entity (category_name) VALUES ('SQL');     --4
INSERT INTO category_entity (category_name) VALUES ('JS');      --5
INSERT INTO category_entity (category_name) VALUES ('ETC');     --6
INSERT INTO category_entity (category_name) VALUES ('PYTHON');  --7
INSERT INTO category_entity (category_name) VALUES ('DB');      --8

INSERT INTO article_entity (title, content, create_At, deleted_At, modified_At, good_Count, bad_Count, view_Count) VALUES ('공지1', '내용1', NOW(),null, NOW(), 0, 0, 0);
INSERT INTO article_category_entity (article_id, category_id) VALUES (1, 1);
INSERT INTO article_entity (title, content, create_At, deleted_At, modified_At, good_Count, bad_Count, view_Count) VALUES ('글1', '내용1', NOW(),null, NOW(), 0, 0, 0);
INSERT INTO article_category_entity (article_id, category_id) VALUES (1, 8);
-- INSERT INTO article_entity (title, content, create_At, deleted_At, modified_At, good_Count, bad_Count, view_Count) VALUES ('공지2', '내용2', NOW(),null, NOW(), 0, 0, 0);
-- INSERT INTO article_category_entity (article_id, category_id) VALUES (1, 2);

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