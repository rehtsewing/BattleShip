var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":371,"id":178,"methods":[{"el":42,"sc":5,"sl":34},{"el":66,"sc":5,"sl":46},{"el":69,"sc":5,"sl":67},{"el":87,"sc":5,"sl":70},{"el":95,"sc":5,"sl":88},{"el":135,"sc":5,"sl":124},{"el":145,"sc":5,"sl":136},{"el":170,"sc":5,"sl":160},{"el":184,"sc":5,"sl":171},{"el":195,"sc":5,"sl":190},{"el":199,"sc":5,"sl":197},{"el":227,"sc":5,"sl":210},{"el":243,"sc":5,"sl":234},{"el":277,"sc":5,"sl":250},{"el":298,"sc":5,"sl":286},{"el":307,"sc":5,"sl":305},{"el":321,"sc":5,"sl":313},{"el":328,"sc":5,"sl":322},{"el":334,"sc":5,"sl":329},{"el":350,"sc":5,"sl":344},{"el":364,"sc":5,"sl":359},{"el":369,"sc":5,"sl":367}],"name":"Client","sl":7}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_19":{"methods":[{"sl":34},{"sl":70},{"sl":88},{"sl":124},{"sl":136}],"name":"testjoinNewGame","pass":true,"statements":[{"sl":35},{"sl":36},{"sl":37},{"sl":38},{"sl":39},{"sl":40},{"sl":41},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":79},{"sl":80},{"sl":82},{"sl":84},{"sl":85},{"sl":86},{"sl":89},{"sl":90},{"sl":94},{"sl":128},{"sl":129},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":134},{"sl":137},{"sl":138},{"sl":139},{"sl":140},{"sl":142},{"sl":143}]},"test_38":{"methods":[{"sl":34},{"sl":46},{"sl":67},{"sl":70},{"sl":88},{"sl":124},{"sl":136},{"sl":160},{"sl":171},{"sl":190},{"sl":197},{"sl":210},{"sl":234},{"sl":250},{"sl":286},{"sl":305},{"sl":313},{"sl":322},{"sl":329},{"sl":344},{"sl":359},{"sl":367}],"name":"testJoinNewGameAndRun","pass":true,"statements":[{"sl":35},{"sl":36},{"sl":37},{"sl":38},{"sl":39},{"sl":40},{"sl":41},{"sl":47},{"sl":48},{"sl":49},{"sl":50},{"sl":52},{"sl":53},{"sl":54},{"sl":55},{"sl":60},{"sl":61},{"sl":68},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":79},{"sl":80},{"sl":82},{"sl":84},{"sl":85},{"sl":89},{"sl":90},{"sl":94},{"sl":128},{"sl":129},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":134},{"sl":137},{"sl":138},{"sl":139},{"sl":140},{"sl":142},{"sl":143},{"sl":161},{"sl":162},{"sl":163},{"sl":165},{"sl":167},{"sl":172},{"sl":173},{"sl":174},{"sl":191},{"sl":192},{"sl":193},{"sl":198},{"sl":211},{"sl":212},{"sl":213},{"sl":214},{"sl":216},{"sl":217},{"sl":218},{"sl":219},{"sl":223},{"sl":224},{"sl":235},{"sl":236},{"sl":237},{"sl":238},{"sl":239},{"sl":240},{"sl":251},{"sl":252},{"sl":253},{"sl":254},{"sl":256},{"sl":257},{"sl":258},{"sl":259},{"sl":260},{"sl":261},{"sl":262},{"sl":263},{"sl":266},{"sl":267},{"sl":268},{"sl":269},{"sl":270},{"sl":271},{"sl":272},{"sl":273},{"sl":274},{"sl":275},{"sl":288},{"sl":289},{"sl":290},{"sl":292},{"sl":293},{"sl":306},{"sl":314},{"sl":315},{"sl":319},{"sl":323},{"sl":324},{"sl":325},{"sl":326},{"sl":330},{"sl":331},{"sl":332},{"sl":333},{"sl":345},{"sl":346},{"sl":347},{"sl":348},{"sl":349},{"sl":360},{"sl":361},{"sl":362},{"sl":363},{"sl":368}]},"test_40":{"methods":[{"sl":34}],"name":"testConstructor","pass":true,"statements":[{"sl":35},{"sl":36},{"sl":37},{"sl":38},{"sl":39},{"sl":40},{"sl":41}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [38, 19, 40], [38, 19, 40], [38, 19, 40], [38, 19, 40], [38, 19, 40], [38, 19, 40], [38, 19, 40], [38, 19, 40], [], [], [], [], [38], [38], [38], [38], [38], [], [38], [38], [38], [38], [], [], [], [], [38], [38], [], [], [], [], [], [38], [38], [], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [], [38, 19], [38, 19], [], [38, 19], [], [38, 19], [38, 19], [19], [], [38, 19], [38, 19], [38, 19], [], [], [], [38, 19], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [38, 19], [], [], [], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [], [38, 19], [38, 19], [38, 19], [38, 19], [38, 19], [], [38, 19], [38, 19], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [], [38], [], [38], [], [], [], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [], [], [], [38], [38], [], [], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [38], [], [38], [38], [38], [38], [], [], [], [38], [38], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [38], [], [38], [38], [38], [38], [38], [38], [38], [38], [], [], [38], [38], [38], [38], [38], [38], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [], [38], [], [38], [38], [38], [], [38], [38], [], [], [], [], [], [], [], [], [], [], [], [38], [38], [], [], [], [], [], [], [38], [38], [38], [], [], [], [38], [], [], [38], [38], [38], [38], [38], [], [], [38], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [38], [38], [], [], [], [], [], [], [], [], [], [38], [38], [38], [38], [38], [], [], [], [38], [38], [], [], [], []]