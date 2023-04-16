var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":36,"id":777,"methods":[{"el":28,"sc":3,"sl":25},{"el":34,"sc":3,"sl":30}],"name":"SimpleShipDisplayInfo","sl":12}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":25}],"name":"test_what_at_enemy_board","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_10":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_11":{"methods":[{"sl":25}],"name":"test_invalid_where","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_12":{"methods":[{"sl":25}],"name":"test_isSunk","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_13":{"methods":[{"sl":25},{"sl":30}],"name":"test_display_enemy","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_14":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_submarine","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_16":{"methods":[{"sl":25}],"name":"test_isSunk","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_18":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_submarine","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_19":{"methods":[{"sl":25}],"name":"testjoinNewGame","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_2":{"methods":[{"sl":25}],"name":"test_invalid_where","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_22":{"methods":[{"sl":25},{"sl":30}],"name":"test_what_at_board","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_23":{"methods":[{"sl":25},{"sl":30}],"name":"test_char_constructor_and_getInfo","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_24":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_battleship","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_28":{"methods":[{"sl":25}],"name":"test_lose_check","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_29":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_battleship","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_33":{"methods":[{"sl":25},{"sl":30}],"name":"test_display_info_at","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_35":{"methods":[{"sl":25},{"sl":30}],"name":"test_add_hide_ship","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_38":{"methods":[{"sl":25},{"sl":30}],"name":"testJoinNewGameAndRun","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_39":{"methods":[{"sl":25},{"sl":30}],"name":"test_chain","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_4":{"methods":[{"sl":25}],"name":"test_hit_at","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_42":{"methods":[{"sl":25}],"name":"test_get_diagonal","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_44":{"methods":[{"sl":25},{"sl":30}],"name":"test_display_info_at","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_45":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_47":{"methods":[{"sl":25}],"name":"test_fire_at","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_48":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_carrier","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_5":{"methods":[{"sl":25},{"sl":30}],"name":"test_takeout_ship","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_51":{"methods":[{"sl":25}],"name":"test_hit_at","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_53":{"methods":[{"sl":25}],"name":"test_get_coordinates","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_54":{"methods":[{"sl":25}],"name":"test_rule_checker","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_56":{"methods":[{"sl":25},{"sl":30}],"name":"test_scan","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_57":{"methods":[{"sl":25}],"name":"test_constructor","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_59":{"methods":[{"sl":25},{"sl":30}],"name":"test_rule_checker","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_6":{"methods":[{"sl":25},{"sl":30}],"name":"test_display_3by5","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_61":{"methods":[{"sl":25}],"name":"test_get_coordinates","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_63":{"methods":[{"sl":25}],"name":"test_constructor","pass":true,"statements":[{"sl":26},{"sl":27}]},"test_7":{"methods":[{"sl":25},{"sl":30}],"name":"test_make_carrier","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_8":{"methods":[{"sl":25},{"sl":30}],"name":"test_display_board_with_enemy_nexttoit","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]},"test_9":{"methods":[{"sl":25},{"sl":30}],"name":"test_no_collision","pass":true,"statements":[{"sl":26},{"sl":27},{"sl":32},{"sl":33}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 23, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 23, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 23, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [], [], [22, 35, 59, 48, 6, 13, 29, 23, 8, 39, 45, 24, 7, 9, 5, 38, 56, 44, 10, 18, 14, 33], [], [22, 35, 59, 48, 6, 13, 29, 23, 8, 39, 45, 24, 7, 9, 5, 38, 56, 44, 10, 18, 14, 33], [22, 35, 59, 48, 6, 13, 29, 23, 8, 39, 45, 24, 7, 9, 5, 38, 56, 44, 10, 18, 14, 33], [], [], []]
