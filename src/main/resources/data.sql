INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user1', 'p1', 'UserOne', 'NickName1', 'USER');
INSERT INTO user_entity (username, password, name, nickName, role) VALUES ('user2', 'p2', 'UserTwo', 'NickName2', 'USER');


INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('제목1', '내용1', NOW(), NOW(), 'ARTICLE');
INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('제목2', '내용2', NOW(), NOW(), 'ARTICLE');
INSERT INTO article_entity (title, content, create_At, modified_At, type) VALUES ('제목3', '내용3', NOW(), NOW(), 'ARTICLE');

/*INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목1', '내용1', NOW(), NOW(), 'ARTICLE');

INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목2', '내용2', NOW(), NOW(), 'ARTICLE');

INSERT INTO article_entity (title, content, createAt, modifiedAt, categorytype)
VALUES ('제목3', '내용3', NOW(), NOW(), 'ARTICLE');
*/