package slidingpuzzle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import slidingpuzzle.background.MeteoroidAnimation
import slidingpuzzle.background.StarsBackground
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.net.URL
import kotlin.random.Random

@Composable
fun PuzzleBoard(value: Int, refreshIndex: Int) {
    val boxWidth = remember { mutableStateOf(-1f) }
    StarsBackground { boxWidth.value = it }
    MeteoroidAnimation(Modifier)
    PuzzleGame(value, boxWidth.value, refreshIndex= refreshIndex)
}

private val words = listOf(
    "Joy: niềm vui",
    "Tea: trà",
    "Sky: bầu trời",
    "Sun: mặt trời",
    "Cat: mèo",
    "Dog: chó",
    "Run: chạy",
    "Key: chìa khóa",
    "Ice: đá lạnh",
    "Day: ngày",
    "Box: hộp",
    "Cup: cốc",
    "Hat: mũ",
    "Lip: môi",
    "Bus: xe buýt",
    "Pen: bút",
    "Man: người đàn ông",
    "Fox: cáo",
    "Ink: mực",
    "Jam: mứt",
    "Toy: đồ chơi",
    "Car: xe hơi",
    "Map: bản đồ",
    "Art: nghệ thuật",
    "Egg: quả trứng",
    "Elf: thần linh",
    "Bow: cung",
    "Fan: quạt",
    "Joy: niềm vui",
    "Lip: môi",
    "Moon: mặt trăng",
    "Song: bài hát",
    "Tree: cây",
    "Fire: lửa",
    "Rose: hoa hồng",
    "Gold: vàng",
    "Baby: em bé",
    "Wind: gió",
    "Rain: mưa",
    "Life: cuộc sống",
    "Blue: màu xanh dương",
    "Hand: bàn tay",
    "Leaf: lá cây",
    "Dark: tối",
    "Gift: quà tặng",
    "Land: đất đai",
    "Star: ngôi sao",
    "Time: thời gian",
    "Eyes: đôi mắt",
    "Bird: chim",
    "Lady: phụ nữ",
    "Mind: tâm trí",
    "Home: nhà",
    "Fish: cá",
    "Skin: da",
    "Song: bài hát",
    "Snow: tuyết",
    "Palm: cây cọ",
    "Soft: mềm mại",
    "Tiger: hổ",
    "Smile: nụ cười",
    "Beach: bãi biển",
    "Music: âm nhạc",
    "Cloud: đám mây",
    "Horse: ngựa",
    "Tiger: hổ",
    "Green: màu xanh lá cây",
    "Angel: thiên thần",
    "Honey: mật ong",
    "Happy: hạnh phúc",
    "Plant: cây cỏ",
    "White: màu trắng",
    "Snake: con rắn",
    "River: con sông",
    "Smile: nụ cười",
    "Beach: bãi biển",
    "Magic: phép màu",
    "Light: ánh sáng",
    "Queen: nữ hoàng",
    "Night: đêm",
    "Space: không gian",
    "Sweet: ngọt ngào",
    "Storm: cơn bão",
    "Water: nước",
    "Heart: trái tim",
    "Fruit: trái cây",
    "Maple: cây phong",
    "Panda: gấu trúc",
    "Ocean: đại dương",
    "Friend: bạn bè",
    "Dragon: con rồng",
    "Banana: chuối",
    "Purple: màu tím",
    "Rocket: tên lửa",
    "Gentle: nhẹ nhàng",
    "Shadow: bóng đen",
    "Cherry: quả anh đào",
    "Turtle: con rùa",
    "Wisdom: sự khôn ngoan",
    "Mellow: êm dịu",
    "Marvel: kỳ diệu",
    "Winter: mùa đông",
    "Forest: khu rừng",
    "Spirit: tâm hồn",
    "Coffee: cà phê",
    "Rocket: tên lửa",
    "Spirit: tâm hồn",
    "Autumn: mùa thu",
    "Gentle: nhẹ nhàng",
    "Breeze: làn gió",
    "Garden: khu vườn",
    "Candle: cây nến",
    "Wisdom: sự khôn ngoan",
    "Travel: du lịch",
    "Island: hòn đảo",
    "Yellow: màu vàng",
    "Puzzle: câu đố",
    "Serene: thanh bình",
    "Sunset: hoàng hôn",
    "Forest: khu rừng",
    "Marvel: kỳ diệu",
    "Blossom: nở hoa",
    "Beauty: vẻ đẹp",
    "Window: cửa sổ",
    "Marvel: kỳ diệu",
    "Spirit: tâm hồn",
    "Gentle: nhẹ nhàng",
    "Wisdom: sự khôn ngoan",
    "Mellow: êm dịu"
)

private fun loadWords(puzzleSize: Int): List<Pair<String, String>> {
    val wordPairlist: MutableList<Pair<String, String>> = mutableStateListOf()
    for (line in words) {
        if (line.contains(":")) {
            val splitedLine = line.split(":")

            val word = splitedLine[0].uppercase()
            val meaning = splitedLine[1].trim()
            if (word.length == puzzleSize) {
                wordPairlist.add(Pair(word, meaning))
            }
        }
    }

    wordPairlist.sortBy { (_) -> Random.nextInt(1, 2) == 1 }
    for (i in 0..wordPairlist.size - 1) {
        val j = (0..wordPairlist.size - 1).random()
        wordPairlist.swap(i, j)
    }

    return wordPairlist.subList(0, puzzleSize)
}

@Composable
fun PuzzleGame(puzzleSize: Int, boxWidth: Float, refreshIndex: Int) {
    val key1 = "$refreshIndex $puzzleSize"
    val wordPairList = remember(key1 = key1) { loadWords(puzzleSize) }

    val moves = remember(key1 = key1) { mutableStateOf(0) }

    val numTiles = puzzleSize * puzzleSize

    val charList: SnapshotStateList<Char> = wordPairList.flatMap { it.first.toList() }.toMutableStateList()

    val emptyTilePosition = remember(key1 = key1) { mutableStateOf(Pair(charList.size - 1, charList.last())) }

    val tileValues = remember(key1 = key1) {
        charList.indices.map { Pair(it, charList[it]) }.toMutableStateList()
    }

    val correctTiles = remember(key1 = key1) { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Puzzle Slide",
            style = MaterialTheme.typography.h4,
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(all = 8.dp))
        Text(
            text = "Solve this Puzzle ",
            style = MaterialTheme.typography.body1,
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(all = 8.dp))
        Text(
            text = "Keywords: " + wordPairList.map { it.second }.joinToString(", "),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(all = 8.dp))
        Row {
//            Text(
//                text = "Time",
//                style = MaterialTheme.typography.body1,
//                fontWeight = FontWeight.W700,
//                color = Color.White,
//            )
            Text(
                text = "Moves:",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "${moves.value}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.W700,
                color = Color.White,
            )
            Text(
                text = "Corrected tiles:",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "${correctTiles.value}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.W700,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        PuzzleTiles(
            puzzleSize,
            boxWidth,
            numTiles,
            tileValues,
            emptyTilePosition,
            correctTiles,
            moves,
            refreshIndex,
        )
    }
}

@Composable
fun PuzzleTiles(
    puzzleSize: Int,
    boxWidth: Float,
    numTiles: Int,
    tileValues: SnapshotStateList<Pair<Int, Char>>,
    emptyTilePosition: MutableState<Pair<Int, Char>>,
    correctTiles: MutableState<Int>,
    moves: MutableState<Int>,
    refreshIndex: Int,
) {
    val puzzleVisibility = remember {
        mutableStateOf(false)
    }

    val key1 = "$refreshIndex $puzzleSize"

    val initialCharList = remember(key1) { tileValues.map { it } }

    fun shuffleTiles() {
        // Shuffle the tiles using Fisher-Yates shuffle algorithm
        for (i in 1..50) {
            val j = (1..numTiles - 3).random()
            tileValues.swap(j, j+1)
        }
        // emptyTilePosition.value = tileValues.indexOf(initialCharList.last())
    }

    LaunchedEffect(key1) {
        shuffleTiles()
    }

    fun canMoveTile(position: Int): Boolean {
        val emptyPosition = emptyTilePosition.value.first
        val row = position / puzzleSize
        val col = position % puzzleSize
        val emptyRow = emptyPosition / puzzleSize
        val emptyCol = emptyPosition % puzzleSize
        return (row == emptyRow && col == emptyCol - 1) || // Left
                (row == emptyRow && col == emptyCol + 1) || // Right
                (row == emptyRow - 1 && col == emptyCol) || // Up
                (row == emptyRow + 1 && col == emptyCol)    // Down
    }

    fun isPuzzleSolved(): Boolean {
        for (i in 0 until initialCharList.size) {
            if (tileValues[i] != initialCharList[i]) return false
        }
        return true
    }

    fun isTileInRightPosition(position: Int): Boolean {
        return tileValues[position].second == initialCharList[position].second
    }

    fun moveTile(position: Int) {
        if (canMoveTile(position)) {
            val emptyPosition = emptyTilePosition.value
            tileValues.swap(emptyPosition.first, position)
            emptyTilePosition.value = Pair(position,  emptyTilePosition.value.second)
            correctTiles.value = countElementsInCorrectPosition(tileValues, expectedTileValues = initialCharList)
            moves.value = moves.value + 1

            if (isPuzzleSolved()) {
                puzzleVisibility.value = true
            }
        }
    }

    val tileSize = remember(boxWidth) { boxWidth / puzzleSize }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        PuzzleSolvedDialog(puzzleVisibility)
        LazyColumn {
            items(puzzleSize) { row ->
                LazyRow {
                    items(puzzleSize) { col ->
                        val position = row * puzzleSize + col
                        val tile = tileValues[position]
                        val isTileInRightPos = isTileInRightPosition(position)
                        val isEmptyTile = tile.first == tileValues.size - 1
                        Box(Modifier.padding(4.dp).border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isEmptyTile) Color.Transparent else Color.White.copy(
                                    alpha = 0.6f
                                ),
                            ), shape = RoundedCornerShape(16.dp)
                        ).width(((tileSize / puzzleSize)).dp).aspectRatio(1f).background(
                            color = if (isEmptyTile) Color.Transparent
                            else if (isTileInRightPos) Color.Gray.copy(
                                0.5f
                            ) else Color.White.copy(
                                alpha = 0.1f
                            ),
                            shape = RoundedCornerShape(16.dp),
                        ).clickable {
                            moveTile(position)
                        }) {
                            val modifier: Modifier
                            if (tile.first != tileValues.size - 1) {
                                modifier = Modifier
                            } else {
                                modifier = Modifier.alpha(0.4f)
                            }
                            Box(modifier = modifier.align(alignment = Alignment.Center)) {
                                Text(
                                    text = tile.second.toString(),
                                    color = Color.White,
                                    fontSize = (tileSize / puzzleSize * 0.3).sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        shuffleTiles()
    }
}

@Composable
fun PuzzleSolvedDialog(
    visible: MutableState<Boolean>,
) {
    if (visible.value) {
        Popup(
            alignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp).background(
                    Color(0xFF202840),
                    shape = RoundedCornerShape(8.dp),
                ),
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Congratulations, you have successfully solved the puzzle",
                        style = MaterialTheme.typography.body1,
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    OutlinedButton(onClick = {
                        visible.value = false
                    }) {
                        Text("OK", style = MaterialTheme.typography.h6.copy(Color(0xFF202840)))
                    }
                }
            }
        }
    }
}

private fun countElementsInCorrectPosition(list: List<Pair<Int, Char?>>, expectedTileValues: List<Pair<Int, Char?>>): Int {
    var count = 0

    for (index in list.indices) {
        val expectedValue = expectedTileValues[index]
        if (list.getOrNull(index) == expectedValue) {
            count++
        }
    }

    return count
}

data class Meteoroid(
    var x: Float, var y: Float, val size: Float, var speed: Float
)

data class Star(
    var x: Float, var y: Float, var radius: Float, var brightness: Float
)

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}
