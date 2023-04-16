var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":123,"id":1989,"methods":[{"el":27,"sc":3,"sl":12},{"el":41,"sc":3,"sl":28},{"el":56,"sc":3,"sl":42},{"el":69,"sc":3,"sl":57},{"el":82,"sc":3,"sl":71},{"el":96,"sc":3,"sl":84},{"el":109,"sc":3,"sl":98},{"el":121,"sc":3,"sl":110}],"name":"AlienShipTest","sl":11}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_15":{"methods":[{"sl":71}],"name":"test_isSunk","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":78},{"sl":79},{"sl":80},{"sl":81}]},"test_17":{"methods":[{"sl":12}],"name":"test_makeCoords","pass":true,"statements":[{"sl":14},{"sl":15},{"sl":16},{"sl":18},{"sl":19},{"sl":20},{"sl":21},{"sl":22},{"sl":23},{"sl":24},{"sl":25},{"sl":26}]},"test_19":{"methods":[{"sl":98}],"name":"test_get_coordinates","pass":true,"statements":[{"sl":100},{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":108}]},"test_30":{"methods":[{"sl":42}],"name":"test_hit_at","pass":true,"statements":[{"sl":44},{"sl":45},{"sl":46},{"sl":47},{"sl":48},{"sl":49},{"sl":50},{"sl":51},{"sl":52},{"sl":53},{"sl":54},{"sl":55}]},"test_31":{"methods":[{"sl":28}],"name":"test_constructor","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":37},{"sl":38},{"sl":39},{"sl":40}]},"test_45":{"methods":[{"sl":84}],"name":"test_display_info_at","pass":true,"statements":[{"sl":86},{"sl":87},{"sl":88},{"sl":89},{"sl":90},{"sl":91},{"sl":92},{"sl":93},{"sl":94},{"sl":95}]},"test_47":{"methods":[{"sl":110}],"name":"test_get_diagonal","pass":true,"statements":[{"sl":112},{"sl":113},{"sl":114},{"sl":115},{"sl":116},{"sl":117},{"sl":118},{"sl":119},{"sl":120}]},"test_62":{"methods":[{"sl":57}],"name":"test_invalid_where","pass":true,"statements":[{"sl":59},{"sl":60},{"sl":61},{"sl":62},{"sl":63},{"sl":64},{"sl":65},{"sl":66},{"sl":67},{"sl":68}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [17], [], [17], [17], [17], [], [17], [17], [17], [17], [17], [17], [17], [17], [17], [], [31], [], [31], [31], [31], [31], [31], [31], [], [31], [31], [31], [31], [], [30], [], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [], [62], [], [62], [62], [62], [62], [62], [62], [62], [62], [62], [62], [], [], [15], [], [15], [15], [15], [15], [15], [15], [15], [15], [15], [], [], [45], [], [45], [45], [45], [45], [45], [45], [45], [45], [45], [45], [], [], [19], [], [19], [19], [19], [19], [19], [19], [19], [19], [19], [], [47], [], [47], [47], [47], [47], [47], [47], [47], [47], [47], [], [], []]
