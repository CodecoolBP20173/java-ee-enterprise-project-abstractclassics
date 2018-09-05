--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: servicecategory; Type: TABLE DATA; Schema: public; Owner: grobadam
--

INSERT INTO public.servicecategory VALUES (2, 'Teaching different subjects.', 'Teaching');
INSERT INTO public.servicecategory VALUES (3, 'Training with a personal trainer.', 'Personal Trainer');
INSERT INTO public.servicecategory VALUES (1, 'Repairs at the household.', 'Household Repairs');
INSERT INTO public.servicecategory VALUES (4, 'Life coaching.', 'Life Coach');


--
-- Data for Name: useraccount; Type: TABLE DATA; Schema: public; Owner: grobadam
--

INSERT INTO public.useraccount VALUES (1, 'a@a.com', '$2a$16$662ajg1YG6RRwNqvyJy3.OllwFYjQc9aj27z5SCp9kKfhnMExLr1G', 'aaa');
INSERT INTO public.useraccount VALUES (2, 'b@b.com', '$2a$16$X6kKyEasAXe0MqDOoVcfjO5e3GtKbqxA1mrSazYUSemzHW0TAL7iO', 'bbb');
INSERT INTO public.useraccount VALUES (3, 'c@c.com', '$2a$16$/8P.E2C7SryJWFsYI1Txr.owq7wG8njs8qg8sgwbAyWukjMnFxilS', 'ccc');
INSERT INTO public.useraccount VALUES (4, 'd@d.com', '$2a$16$RMYitn16D8TRveXR1h9bQOZLeAVTo2tDjOqJG6C8RmVx/AuCC6KaS', 'ddd');


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: grobadam
--

INSERT INTO public.service VALUES (2, 'Help you gain muscle mass.', 'Body Building Trainer', 1, 25, 5, 3, 2);
INSERT INTO public.service VALUES (3, 'Offering plumbing services.', 'Plumbing Services', 0, 25, 0, 1, 3);
INSERT INTO public.service VALUES (4, 'Based on my life experience and great willpower I am offering life coaching advises.', 'Life Coaching', 0, 44, 0, 4, 2);
INSERT INTO public.service VALUES (5, 'Singing lessons from a Grammy Award winner! ', 'Singing Lessons', 0, 1111, 0, 2, 4);
INSERT INTO public.service VALUES (1, 'Teaching the arts of playing cello.', 'Cello Teaching', 3, 20, 12, 2, 1);


--
-- Data for Name: rating; Type: TABLE DATA; Schema: public; Owner: grobadam
--

INSERT INTO public.rating VALUES (1, 1, 1);
INSERT INTO public.rating VALUES (2, 1, 2);
INSERT INTO public.rating VALUES (3, 2, 2);
INSERT INTO public.rating VALUES (4, 1, 4);


--
-- Data for Name: userdetail; Type: TABLE DATA; Schema: public; Owner: grobadam
--

INSERT INTO public.userdetail VALUES ('Budapest', '2018-08-27', 'Adam', 'MALE', 'https://media.discordapp.net/attachments/403699436468830210/482823365623545856/ZS_7764_pp.jpg?width=999&height=666', 'Hi, my name is Adam, and I offer music teaching services.', 'Grob', '34234243242', 1);
INSERT INTO public.userdetail VALUES (' Brooklyn', '2018-12-30', 'Mario', 'MALE', 'https://mario.nintendo.com/assets/img/home/intro/mario-pose2.png', 'Mario is depicted as a portly plumber who lives in the fictional land of the Mushroom Kingdom with Luigi, his younger, taller brother.[3][56][57] In the television series and film, Mario and Luigi are originally from Brooklyn, New York.[56] Little is known of Mario''s childhood, though the infant version of Mario, Baby Mario, first appeared in 1995 in Super Mario World 2: Yoshi''s Island, and has often appeared in Nintendo sports games since then. Baby Mario has a major role along with Baby Luigi  ....', 'Mario', '2564252445', 3);
INSERT INTO public.userdetail VALUES ('Los Angeles', '2018-08-27', 'Arnold', 'MALE', 'https://naqyr37xcg93tizq734pqsx1-wpengine.netdna-ssl.com/wp-content/uploads/2016/02/62-Unforgettable-Arnold-Schwarzenegger-Quotes.jpg', 'Hi, my name is Arnold and I am offering personal training and life coaching services', 'Schwarzenegger', '32454325234532', 2);
INSERT INTO public.userdetail VALUES ('New York', '2018-08-30', 'Alicia', 'FEMALE', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/pictured-alicia-keys-news-photo-592222308-fix-1532453542.jpg', 'Alicia Keys is a 15-time Grammy® Award-winning singer/songwriter/producer, an accomplished actress, a New York Times best-selling author, an entrepreneur and a powerful force in the world of philanthropy and in the global fight against HIV and AIDS.

', 'Keys', '2452345234523', 4);


--
-- Name: rating_id_seq; Type: SEQUENCE SET; Schema: public; Owner: grobadam
--

SELECT pg_catalog.setval('public.rating_id_seq', 4, true);


--
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: grobadam
--

SELECT pg_catalog.setval('public.service_id_seq', 5, true);


--
-- Name: servicecategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: grobadam
--

SELECT pg_catalog.setval('public.servicecategory_id_seq', 4, true);


--
-- Name: useraccount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: grobadam
--

SELECT pg_catalog.setval('public.useraccount_id_seq', 4, true);


--
-- PostgreSQL database dump complete
--

