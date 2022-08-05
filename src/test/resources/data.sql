--This script file executed after the one in main/resources

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    3,
    1,
    3,
    CURRENT_TIMESTAMP
);