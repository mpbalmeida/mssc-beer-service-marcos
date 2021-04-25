-- INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price,
--                   upc, version)
-- values ('0a818933087d47f2ad832f986ed087eb', 'Mango Bobs', 'IPA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12, 200,
--         12.95, '0631234200036', 1);
-- INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price,
--                   upc, version)
-- values ('a712d91461ea46238bd032c0f6545bfd', 'Galaxy Cat', 'PALE_ALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12, 200,
--         12.95, '0631234300019', 1);
-- INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price,
--                   upc, version)
-- values ('026cc3c83a0c4083a05be908048c1b08', 'Pinball Porter', 'PORTER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12,
--         200, 12.95, '0083783375213', 1);

INSERT INTO beerservice.beer (id,beer_name,beer_style,created_date,last_modified_date,min_on_hand,price,quantity_to_brew,upc,version) VALUES
(UUID_TO_BIN('0a818933-087d-47f2-ad83-2f986ed087eb'),'No Hammers On The Bar','PALE_ALE','2021-04-25 12:21:22.641000000','2021-04-25 12:21:22.641000000',20,11.95,100,'0083783375213',0),
(UUID_TO_BIN('a712d914-61ea-4623-8bd0-32c0f6545bfd'),'Mango Bobs','IPA','2021-04-25 12:21:22.619000000','2021-04-25 12:21:22.619000000',12,12.95,200,'0631234200036',0),
(UUID_TO_BIN('026cc3c8-3a0c-4083-a05b-e908048c1b08'),'Galaxy Cat','PALE_ALE','2021-04-25 12:21:22.637000000','2021-04-25 12:21:22.637000000',20,11.95,100,'0631234300019',0);