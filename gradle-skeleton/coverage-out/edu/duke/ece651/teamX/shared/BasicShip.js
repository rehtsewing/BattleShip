var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":138,"id":890,"methods":[{"el":47,"sc":3,"sl":38},{"el":58,"sc":3,"sl":48},{"el":71,"sc":3,"sl":67},{"el":77,"sc":3,"sl":74},{"el":83,"sc":3,"sl":80},{"el":88,"sc":3,"sl":85},{"el":96,"sc":3,"sl":93},{"el":104,"sc":3,"sl":98},{"el":110,"sc":3,"sl":106},{"el":116,"sc":3,"sl":112},{"el":126,"sc":3,"sl":118},{"el":137,"sc":3,"sl":128}],"name":"BasicShip","sl":6}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":38},{"sl":85},{"sl":93}],"name":"test_what_at_enemy_board","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":87},{"sl":95}]},"test_10":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_11":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":112},{"sl":118}],"name":"test_invalid_where","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":69},{"sl":108},{"sl":114},{"sl":120}]},"test_12":{"methods":[{"sl":38},{"sl":67},{"sl":98},{"sl":106}],"name":"test_isSunk","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":100},{"sl":101},{"sl":103},{"sl":108},{"sl":109}]},"test_13":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":106},{"sl":118}],"name":"test_display_enemy","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":108},{"sl":109},{"sl":120},{"sl":121},{"sl":122},{"sl":124}]},"test_14":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_submarine","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_16":{"methods":[{"sl":38},{"sl":67},{"sl":98},{"sl":106}],"name":"test_isSunk","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":100},{"sl":101},{"sl":103},{"sl":108},{"sl":109}]},"test_18":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_submarine","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_19":{"methods":[{"sl":38},{"sl":85},{"sl":93}],"name":"testjoinNewGame","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":87},{"sl":95}]},"test_2":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":112},{"sl":118}],"name":"test_invalid_where","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":69},{"sl":108},{"sl":114},{"sl":120}]},"test_22":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_what_at_board","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]},"test_24":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_battleship","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_28":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":98},{"sl":106}],"name":"test_lose_check","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":100},{"sl":101},{"sl":103},{"sl":108},{"sl":109}]},"test_29":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_battleship","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_33":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":118}],"name":"test_display_info_at","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":108},{"sl":109},{"sl":120},{"sl":121},{"sl":122},{"sl":124}]},"test_35":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":106},{"sl":112},{"sl":118}],"name":"test_add_hide_ship","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":108},{"sl":109},{"sl":114},{"sl":115},{"sl":120},{"sl":121},{"sl":122},{"sl":124}]},"test_38":{"methods":[{"sl":38},{"sl":48},{"sl":67},{"sl":74},{"sl":80},{"sl":85},{"sl":93},{"sl":98},{"sl":106},{"sl":112},{"sl":118},{"sl":128}],"name":"testJoinNewGameAndRun","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":49},{"sl":50},{"sl":51},{"sl":52},{"sl":54},{"sl":55},{"sl":56},{"sl":57},{"sl":68},{"sl":76},{"sl":82},{"sl":87},{"sl":95},{"sl":100},{"sl":101},{"sl":108},{"sl":109},{"sl":114},{"sl":115},{"sl":120},{"sl":121},{"sl":122},{"sl":124},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":134},{"sl":136}]},"test_39":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_chain","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]},"test_4":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":112}],"name":"test_hit_at","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":108},{"sl":109},{"sl":114},{"sl":115}]},"test_42":{"methods":[{"sl":38},{"sl":128}],"name":"test_get_diagonal","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":134},{"sl":136}]},"test_44":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":118}],"name":"test_display_info_at","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":108},{"sl":109},{"sl":120},{"sl":121},{"sl":122},{"sl":124}]},"test_45":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_47":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":98},{"sl":106}],"name":"test_fire_at","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":100},{"sl":101},{"sl":103},{"sl":108},{"sl":109}]},"test_48":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_carrier","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_5":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_takeout_ship","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":124}]},"test_51":{"methods":[{"sl":38},{"sl":67},{"sl":106},{"sl":112}],"name":"test_hit_at","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":108},{"sl":109},{"sl":114},{"sl":115}]},"test_53":{"methods":[{"sl":38},{"sl":85}],"name":"test_get_coordinates","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":87}]},"test_54":{"methods":[{"sl":38},{"sl":85}],"name":"test_rule_checker","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":87}]},"test_56":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_scan","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]},"test_57":{"methods":[{"sl":38},{"sl":93}],"name":"test_constructor","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":95}]},"test_59":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_rule_checker","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]},"test_6":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_display_3by5","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]},"test_61":{"methods":[{"sl":38},{"sl":85}],"name":"test_get_coordinates","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":87}]},"test_63":{"methods":[{"sl":38},{"sl":93}],"name":"test_constructor","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":95}]},"test_7":{"methods":[{"sl":38},{"sl":67},{"sl":118}],"name":"test_make_carrier","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":120},{"sl":121},{"sl":122}]},"test_8":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":106},{"sl":118}],"name":"test_display_board_with_enemy_nexttoit","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":108},{"sl":109},{"sl":120},{"sl":121},{"sl":122}]},"test_9":{"methods":[{"sl":38},{"sl":67},{"sl":85},{"sl":93},{"sl":118}],"name":"test_no_collision","pass":true,"statements":[{"sl":39},{"sl":40},{"sl":41},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":68},{"sl":87},{"sl":95},{"sl":120},{"sl":121},{"sl":122}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 61, 35, 59, 48, 28, 6, 13, 29, 57, 8, 39, 45, 1, 24, 7, 9, 11, 2, 5, 38, 53, 56, 42, 44, 19, 54, 63, 10, 47, 51, 12, 16, 18, 14, 33], [], [38], [38], [38], [38], [38], [], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [4, 22, 35, 59, 48, 28, 6, 13, 29, 8, 39, 45, 24, 7, 9, 11, 2, 5, 38, 56, 44, 10, 47, 51, 12, 16, 18, 14, 33], [4, 22, 35, 59, 48, 28, 6, 13, 29, 8, 39, 45, 24, 7, 9, 11, 2, 5, 38, 56, 44, 10, 47, 51, 12, 16, 18, 14, 33], [11, 2], [], [], [], [], [38], [], [38], [], [], [], [38], [], [38], [], [], [22, 61, 35, 59, 28, 6, 13, 8, 39, 1, 9, 5, 38, 53, 56, 19, 54, 47], [], [22, 61, 35, 59, 28, 6, 13, 8, 39, 1, 9, 5, 38, 53, 56, 19, 54, 47], [], [], [], [], [], [22, 35, 59, 28, 6, 13, 57, 8, 39, 1, 9, 5, 38, 56, 19, 63, 47], [], [22, 35, 59, 28, 6, 13, 57, 8, 39, 1, 9, 5, 38, 56, 19, 63, 47], [], [], [28, 38, 47, 12, 16], [], [28, 38, 47, 12, 16], [28, 38, 47, 12, 16], [], [28, 47, 12, 16], [], [], [4, 35, 28, 13, 8, 11, 2, 38, 44, 47, 51, 12, 16, 33], [], [4, 35, 28, 13, 8, 11, 2, 38, 44, 47, 51, 12, 16, 33], [4, 35, 28, 13, 8, 38, 44, 47, 51, 12, 16, 33], [], [], [4, 35, 11, 2, 38, 51], [], [4, 35, 11, 2, 38, 51], [4, 35, 38, 51], [], [], [22, 35, 59, 48, 6, 13, 29, 8, 39, 45, 24, 7, 9, 11, 2, 5, 38, 56, 44, 10, 18, 14, 33], [], [22, 35, 59, 48, 6, 13, 29, 8, 39, 45, 24, 7, 9, 11, 2, 5, 38, 56, 44, 10, 18, 14, 33], [22, 35, 59, 48, 6, 13, 29, 8, 39, 45, 24, 7, 9, 5, 38, 56, 44, 10, 18, 14, 33], [22, 35, 59, 48, 6, 13, 29, 8, 39, 45, 24, 7, 9, 38, 56, 44, 10, 18, 14, 33], [], [35, 13, 5, 38, 44, 33], [], [], [], [38, 42], [], [38, 42], [38, 42], [38, 42], [38, 42], [38, 42], [], [38, 42], [], []]
