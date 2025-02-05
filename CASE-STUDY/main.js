class Cell {
    constructor(row, col, current) {
        this.row = row;
        this.col = col;
        this.currentNumber = current;
        this.isStartNumber = false;
    }
}

const array = [];
for (let i = 1; i <= 9; i++) {
    for (let j = 1; j <= 9; j++) {
        array.push(new Cell(i, j, ""));
    }
}

const table = document.getElementById("table");

function createRandomNumber(how) {
    for (let i = 0; i < how; i++) {
        let randomIndex = Math.floor(Math.random() * 81);
        if (!array[randomIndex].currentNumber) {
            const crossNumbers = findCross(array[randomIndex]).map(item => item.currentNumber);
            const sameSquareNumbers = findSameSquares(array[randomIndex]).map(item => item.currentNumber);
            let newNumber;
            let retry = 0;
            let isOkNumber;
            while (retry < 50) {
                newNumber = Math.ceil(Math.random() * 9);
                if (crossNumbers.includes(newNumber) || sameSquareNumbers.includes(newNumber)) {
                    isOkNumber = false
                    retry++;
                } else {
                    isOkNumber = true;
                    array[randomIndex].isStartNumber = true;
                    array[randomIndex].currentNumber = newNumber;
                    break
                }
            }
        }
    }
}

function findSameSquares(cell) {
    let temp;
    switch (cell.row) {
        case 1:
        case 2:
        case 3:
            switch (cell.col) {
                case 1:
                case 2:
                case 3:
                    temp = array.filter(item => item !== cell && item.row < 4 && item.col < 4);
                    break;
                case 4:
                case 5:
                case 6:
                    temp = array.filter(item => item !== cell && item.row < 4 && item.col > 3 && item.col < 7);
                    break;
                default:
                    temp = array.filter(item => item !== cell && item.row < 4 && item.col > 6);
            }
            break;
        case 4:
        case 5:
        case 6:
            switch (cell.col) {
                case 1:
                case 2:
                case 3:
                    temp = array.filter(item => item !== cell && item.row > 3 && item.row < 7 && item.col < 4);
                    break;
                case 4:
                case 5:
                case 6:
                    temp = array.filter(item => item !== cell && item.row > 3 && item.row < 7 && item.col > 3 && item.col < 7);
                    break;
                default:
                    temp = array.filter(item => item !== cell && item.row > 3 && item.row < 7 && item.col > 6);
            }
            break;
        default:
            switch (cell.col) {
                case 1:
                case 2:
                case 3:
                    temp = array.filter(item => item !== cell && item.row > 6 && item.col < 4);
                    break;
                case 4:
                case 5:
                case 6:
                    temp = array.filter(item => item !== cell && item.row > 6 && item.col > 3 && item.col < 7);
                    break;
                default:
                    temp = array.filter(item => item !== cell && item.row > 6 && item.col > 6);
            }
    }
    return temp.filter(item => item.currentNumber !== "");
}

function findCross(cell) {
    let temp;
    temp = array.filter(item =>
        (item.row === cell.row && item.col !== cell.col) ||
        (item.col === cell.col && item.row !== cell.row))
    return temp.filter(item => item.currentNumber !== "");
}


function highlight(row, col, over) {
    document.querySelectorAll("td").forEach(item => {
        const itemRow = item.getAttribute("data-row");
        const itemCol = item.getAttribute("data-col");
        if (itemRow == row || itemCol == col) {
            if (over) {
                item.classList.add("highlight");
            } else {
                item.classList.remove("highlight");
            }
        }
    });
}

let count = 0
let gameStarted = false;

function gameRuning() {
    if (!gameStarted) {
        return
    }
    table.innerHTML = "";
    for (let i = 1; i <= 9; i++) {
        const tr = document.createElement("tr");
        const filterRow = array.filter(item => item.row === i);
        for (let item of filterRow) {
            const td = document.createElement("td");
            td.innerHTML = item.currentNumber;
            td.setAttribute("data-row", item.row);
            td.setAttribute("data-col", item.col);
            if (item.currentNumber !== "") {
                td.classList.add("startnumber");
            }
            if (!item.isStartNumber) {
                td.onclick = function () {
                    const inputNumber = prompt("Nhập số muốn điền vào ô trống");
                    if (!inputNumber) {
                        return;
                    }
                    const reg = /^[1-9]$/;
                    if (!reg.test(inputNumber)) {
                        alert("Số không hợp lệ");
                        return;
                    }
                    if (count >= 2) {
                        alert("Hết lượt nhập sai, chọn lại độ khó để bắt đầu trò chơi mới");
                        return;
                    }

                    let isInvalid = false;
                    const findCrossSquareNumbers =
                        findCross(item).concat(findSameSquares(item)).map(each => each.currentNumber)
                    for (let each of findCrossSquareNumbers) {
                        if (each === +inputNumber) {
                            isInvalid = true;
                            break
                        }
                    }
                    if (isInvalid) {
                        count++;
                        td.style.color = "red";
                        td.innerHTML = inputNumber;
                        return;
                    }
                    td.style.color = "blue";
                    td.innerHTML = inputNumber;
                    item.currentNumber = +inputNumber;
                    checkWin()
                };
            }
            td.addEventListener("mouseover", function () {
                highlight(item.row, item.col, true);
            });
            td.addEventListener("mouseout", function () {
                highlight(item.row, item.col, false);
            });
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
    addBoldBorders()
}

function newGame(how) {
    gameStarted = true;
    count = 0;
    for (let item of array) {
        item.currentNumber = "";
        item.isStartNumber = false;
    }
    document.querySelectorAll("td").forEach(item => {
        item.innerHTML = "";
        item.classList.remove("startnumber");
        item.onclick = null;
        item.onmouseover = null;
        item.onmouseout = null;
    });
    createRandomNumber(how);
    gameRuning();
}

function checkWin() {
    for (let item of array) {
        if (!item.currentNumber) {
            return;
        }
    }
    alert("Xuất sắc, bạn đã hoàn thành màn chơi")
}

function addBoldBorders() {
    document.querySelectorAll("td").forEach(td => {
        const row = +(td.getAttribute("data-row"));
        const col = +(td.getAttribute("data-col"));
        if (row % 3 === 1) td.style.borderTop = "5px solid black";
        if (col % 3 === 1) td.style.borderLeft = "5px solid black";
        if (row % 3 === 0) td.style.borderBottom = "5px solid black";
        if (col % 3 === 0) td.style.borderRight = "5px solid black";
    });
}
