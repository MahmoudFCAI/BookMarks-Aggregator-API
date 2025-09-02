INSERT into users (username,email,phone,password) values ('Mahmoud','m.ali@gmail.com','01156542833','20230374');
INSERT into users (username,email,phone,password) values ('Ahmed','a.ali@gmail.com','01154542833','20230371');
INSERT into users (username,email,phone,password) values ('Mohamed','mo.ali@gmail.com','01114542833','20230372');
INSERT into users (username,email,phone,password) values ('Yusef','y.ali@gmail.com','01114742833','20230373');


INSERT INTO Bookmarks (url, name, des, type, user_id)VALUES ('https://www.google.com', 'Google', 'Search Engine', 'Website', 1);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://www.youtube.com', 'YouTube', 'Video Sharing', 'Website', 2);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://spring.io', 'Spring', 'Spring Framework Docs', 'Website', 3);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://github.com', 'GitHub', 'Code Hosting Platform', 'Website', 2);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://stackoverflow.com', 'Stack Overflow', 'Programming Q&A', 'Website',  2);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://chat.openai.com', 'ChatGPT', 'AI Chatbot', 'Website', 1);
INSERT INTO Bookmarks (url, name, des, type, user_id) VALUES('https://news.ycombinator.com', 'Hacker News', 'Tech News', 'Website', 3);


INSERT INTO tags (name) VALUES ('search');
INSERT INTO tags (name) VALUES ('tech');
INSERT INTO tags (name) VALUES ('education');
INSERT INTO tags (name) VALUES ('java');
INSERT INTO tags (name) VALUES ('c++');
INSERT INTO tags (name) VALUES ('c#');


INSERT INTO mark_tags (mark_id, tag_id) VALUES (1, 1);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (1, 2);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (2, 4);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (2, 5);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (3, 4);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (4, 5);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (5, 6);
INSERT INTO mark_tags (mark_id, tag_id) VALUES (6, 6);
