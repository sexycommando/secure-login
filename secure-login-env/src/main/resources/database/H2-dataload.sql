INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Taro', 'Yamada', 'taro.yamada@localhost.localdomain', 'http://securelogin.sample.jp', 'I am an user.');
INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Jiro', 'Sato', 'jiro.sato@localhost.localdomain', 'http://sample.application.jp', 'I am an administrator.');

INSERT INTO account_image(username, body, extension) VALUES ('demo', FILE_READ('classpath:database/testdata/demo.png'), 'png');
INSERT INTO account_image(username, body, extension) VALUES ('admin', FILE_READ('classpath:database/testdata/admin.png'), 'png');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');

-- INSERT INTO password_history(username, password, use_from) VALUES ('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', to_date('2017-07-31 18:49:06.051','yyyy/mm/dd hh24:mi:ss'));
-- INSERT INTO password_history(username, password, use_from) VALUES ('demo01', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', to_date('2017-07-31 18:49:06.051','yyyy/mm/dd hh24:mi:ss'));
-- INSERT INTO password_history(username, password, use_from) VALUES ('demo02', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', to_date('2017-07-31 18:49:06.051','yyyy/mm/dd hh24:mi:ss'));

COMMIT;
