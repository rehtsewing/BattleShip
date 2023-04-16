var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":205,"id":1927,"methods":[{"el":18,"sc":3,"sl":13},{"el":25,"sc":3,"sl":19},{"el":34,"sc":3,"sl":27},{"el":40,"sc":3,"sl":35},{"el":55,"sc":3,"sl":41},{"el":75,"sc":3,"sl":56},{"el":96,"sc":3,"sl":76},{"el":146,"sc":3,"sl":97},{"el":160,"sc":3,"sl":147},{"el":171,"sc":3,"sl":161},{"el":186,"sc":3,"sl":172},{"el":204,"sc":3,"sl":187}],"name":"BattleShipBoardTest","sl":12}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_10":{"methods":[{"sl":161}],"name":"test_what_at_enemy_board","pass":true,"statements":[{"sl":163},{"sl":164},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":169},{"sl":170}]},"test_15":{"methods":[{"sl":56}],"name":"test_rule_checker","pass":true,"statements":[{"sl":58},{"sl":59},{"sl":60},{"sl":61},{"sl":62},{"sl":63},{"sl":64},{"sl":65},{"sl":66},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":71},{"sl":72},{"sl":73},{"sl":74}]},"test_16":{"methods":[{"sl":27},{"sl":35},{"sl":41}],"name":"test_what_at_board","pass":true,"statements":[{"sl":28},{"sl":29},{"sl":30},{"sl":31},{"sl":36},{"sl":37},{"sl":38},{"sl":39},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":47},{"sl":48},{"sl":50},{"sl":51},{"sl":52},{"sl":53},{"sl":54}]},"test_20":{"methods":[{"sl":97}],"name":"test_add_hide_ship","pass":true,"statements":[{"sl":99},{"sl":100},{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111},{"sl":112},{"sl":114},{"sl":115},{"sl":116},{"sl":119},{"sl":120},{"sl":121},{"sl":122},{"sl":123},{"sl":124},{"sl":125},{"sl":126},{"sl":128},{"sl":129},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":134},{"sl":135},{"sl":136},{"sl":137},{"sl":139},{"sl":140},{"sl":141},{"sl":142},{"sl":143},{"sl":144}]},"test_24":{"methods":[{"sl":13}],"name":"test_width_and_height","pass":true,"statements":[{"sl":15},{"sl":16},{"sl":17}]},"test_25":{"methods":[{"sl":76}],"name":"test_fire_at","pass":true,"statements":[{"sl":78},{"sl":79},{"sl":80},{"sl":81},{"sl":82},{"sl":83},{"sl":84},{"sl":85},{"sl":86},{"sl":87},{"sl":88},{"sl":90},{"sl":91},{"sl":92},{"sl":93},{"sl":94},{"sl":95}]},"test_30":{"methods":[{"sl":187}],"name":"test_scan","pass":true,"statements":[{"sl":189},{"sl":190},{"sl":191},{"sl":192},{"sl":193},{"sl":194},{"sl":195},{"sl":196},{"sl":197},{"sl":198},{"sl":199},{"sl":200},{"sl":201},{"sl":202},{"sl":203}]},"test_33":{"methods":[{"sl":147}],"name":"test_takeout_ship","pass":true,"statements":[{"sl":149},{"sl":150},{"sl":151},{"sl":152},{"sl":153},{"sl":154},{"sl":155},{"sl":156},{"sl":157},{"sl":158},{"sl":159}]},"test_38":{"methods":[{"sl":19}],"name":"test_invalid_dimension","pass":true,"statements":[{"sl":21},{"sl":22},{"sl":23},{"sl":24}]},"test_42":{"methods":[{"sl":172}],"name":"test_lose_check","pass":true,"statements":[{"sl":174},{"sl":175},{"sl":176},{"sl":177},{"sl":178},{"sl":179},{"sl":180},{"sl":181},{"sl":182},{"sl":183},{"sl":184},{"sl":185}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [24], [], [24], [24], [24], [], [38], [], [38], [38], [38], [38], [], [], [16], [16], [16], [16], [16], [], [], [], [16], [16], [16], [16], [16], [], [16], [], [16], [16], [16], [16], [16], [16], [], [16], [16], [16], [16], [16], [], [15], [], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [15], [], [25], [], [25], [25], [25], [25], [25], [25], [25], [25], [25], [25], [25], [], [25], [25], [25], [25], [25], [25], [], [20], [], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [], [20], [20], [20], [], [], [20], [20], [20], [20], [20], [20], [20], [20], [], [20], [20], [20], [20], [20], [20], [20], [20], [20], [20], [], [20], [20], [20], [20], [20], [20], [], [], [33], [], [33], [33], [33], [33], [33], [33], [33], [33], [33], [33], [33], [], [10], [], [10], [10], [10], [10], [10], [10], [10], [10], [], [42], [], [42], [42], [42], [42], [42], [42], [42], [42], [42], [42], [42], [42], [], [30], [], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [], []]
