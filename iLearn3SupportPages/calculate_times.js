function calculateTimes(){
	var tables = document.querySelectorAll(".activity_table")
	var numTables = tables.length
	for(var i = 0; i < numTables; i++){
		var studentMinutes = 0
		var instructorMinutes = 0
		var aTable = tables[i]
		var rows = aTable.querySelectorAll("tr")
		var numRows = rows.length
		for(var j = 0; j < numRows; j++){
			var aRow = rows[j]
			if(aRow.className == "activity_table_header"){
				continue
			}
			var tableDatas = aRow.querySelectorAll("td")
			studentMinutes += tableDatas[3].innerHTML *1
			instructorMinutes += tableDatas[4].innerHTML *1
		}
		aTable.querySelector(".student_hours").innerHTML = (studentMinutes/60).toFixed(1)
		aTable.querySelector(".instructor_hours").innerHTML = (instructorMinutes/60).toFixed(1)
	}
}