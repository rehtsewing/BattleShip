var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":121,"id":534,"methods":[{"el":25,"sc":3,"sl":21},{"el":57,"sc":3,"sl":41},{"el":67,"sc":3,"sl":65},{"el":76,"sc":3,"sl":74},{"el":90,"sc":3,"sl":83},{"el":108,"sc":3,"sl":99},{"el":119,"sc":3,"sl":116}],"name":"Placement","sl":3}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_chain","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_10":{"methods":[{"sl":21},{"sl":74}],"name":"test_make_invalid_ship","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":75}]},"test_11":{"methods":[{"sl":21},{"sl":99}],"name":"test_toString","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107}]},"test_13":{"methods":[{"sl":21},{"sl":83}],"name":"test_equals","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":85},{"sl":86},{"sl":87},{"sl":89}]},"test_14":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_submarine","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_20":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_where_and_orientation","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_21":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_no_collision","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_22":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_carrier","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_25":{"methods":[{"sl":41},{"sl":65},{"sl":74}],"name":"testJoinNewGameAndRun","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":46},{"sl":47},{"sl":48},{"sl":49},{"sl":52},{"sl":55},{"sl":56},{"sl":66},{"sl":75}]},"test_26":{"methods":[{"sl":41},{"sl":65},{"sl":74}],"name":"testjoinNewGame","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":46},{"sl":47},{"sl":48},{"sl":49},{"sl":52},{"sl":55},{"sl":56},{"sl":66},{"sl":75}]},"test_28":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_carrier","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_29":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_scan","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_33":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_34":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_battleship","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_36":{"methods":[{"sl":21},{"sl":99},{"sl":116}],"name":"test_hashCode","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":118}]},"test_39":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_what_at_enemy_board","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_4":{"methods":[{"sl":41}],"name":"test_string_constructor_error_cases","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":44},{"sl":46},{"sl":47},{"sl":48},{"sl":49},{"sl":50},{"sl":52},{"sl":53}]},"test_41":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_add_hide_ship","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_43":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_rule_checker","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_46":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_lose_check","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_48":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_fire_at","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_5":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_display_enemy","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_52":{"methods":[{"sl":21},{"sl":41},{"sl":83}],"name":"test_string_constructor_valid_cases","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":42},{"sl":43},{"sl":46},{"sl":47},{"sl":48},{"sl":49},{"sl":52},{"sl":55},{"sl":56},{"sl":85},{"sl":86},{"sl":87}]},"test_54":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_battleship","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_58":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_takeout_ship","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_59":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_submarine","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_61":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_display_board_with_enemy_nexttoit","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_64":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_make_destroyer","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]},"test_7":{"methods":[{"sl":21},{"sl":65},{"sl":74}],"name":"test_rule_checker","pass":true,"statements":[{"sl":22},{"sl":23},{"sl":24},{"sl":66},{"sl":75}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [46, 64, 58, 5, 20, 11, 39, 13, 48, 21, 43, 1, 7, 22, 41, 36, 33, 52, 29, 14, 28, 10, 34, 61, 54, 59], [46, 64, 58, 5, 20, 11, 39, 13, 48, 21, 43, 1, 7, 22, 41, 36, 33, 52, 29, 14, 28, 10, 34, 61, 54, 59], [46, 64, 58, 5, 20, 11, 39, 13, 48, 21, 43, 1, 7, 22, 41, 36, 33, 52, 29, 14, 28, 10, 34, 61, 54, 59], [46, 64, 58, 5, 20, 11, 39, 13, 48, 21, 43, 1, 7, 22, 41, 36, 33, 52, 29, 14, 28, 10, 34, 61, 54, 59], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [4, 25, 52, 26], [4, 25, 52, 26], [4, 25, 52, 26], [4], [], [4, 25, 52, 26], [4, 25, 52, 26], [4, 25, 52, 26], [4, 25, 52, 26], [4], [], [4, 25, 52, 26], [4], [], [25, 52, 26], [25, 52, 26], [], [], [], [], [], [], [], [], [46, 64, 58, 5, 20, 39, 48, 21, 43, 1, 7, 22, 25, 41, 33, 29, 14, 28, 26, 34, 61, 54, 59], [46, 64, 58, 5, 20, 39, 48, 21, 43, 1, 7, 22, 25, 41, 33, 29, 14, 28, 26, 34, 61, 54, 59], [], [], [], [], [], [], [], [46, 64, 58, 5, 20, 39, 48, 21, 43, 1, 7, 22, 25, 41, 33, 29, 14, 28, 26, 10, 34, 61, 54, 59], [46, 64, 58, 5, 20, 39, 48, 21, 43, 1, 7, 22, 25, 41, 33, 29, 14, 28, 26, 10, 34, 61, 54, 59], [], [], [], [], [], [], [], [13, 52], [], [13, 52], [13, 52], [13, 52], [], [13], [], [], [], [], [], [], [], [], [], [11, 36], [], [11, 36], [11, 36], [11, 36], [11, 36], [11, 36], [11, 36], [11, 36], [], [], [], [], [], [], [], [], [36], [], [36], [], [], []]
