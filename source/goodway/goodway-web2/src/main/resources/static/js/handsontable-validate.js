
// Mẫu để kiểm tra số thực
var floatPattern = /^[\d]*[\.]{0,1}[\d]*$/;
var intPattern = /^[\d]*$/;
/**
 * Check value is a positive integer.
 * 
 * @param value number to be checked
 * @param callback function of caller.
 */
intValidator = function (value, callback) {
	var pattern = /^[\d]*$/;
	
	if (pattern.test(value)) {
	  callback(true);
	} else {
	  callback(false);
	}
};

intValidator4 = function (value, callback) {
	var pattern = /^[\d]*$/;
	
	if (pattern.test(value) && value.length <= 4) {
	  callback(true);
	} else {
	  callback(false);
	}
};

intValidator5 = function (value, callback) {
	var pattern = /^[\d]*$/;
	
	if (pattern.test(value) && value.length <= 5) {
	  callback(true);
	} else {
	  callback(false);
	}
};

floatValidator = function (value, callback) {
	if (floatPattern.test(value)) {
	  callback(true);
	} else {
	  callback(false);
	}
};



lengthValidateName = function(value, callback) {
	(value.length <= lenName) ? ($('#errorName').hide() , callback(true)) : ($('#errorName').show() , callback(false));
};

lengthValidateDes = function(value, callback) {
	(value.length <= lenDes) ? ($('#errorDes').hide() , callback(true)) : ($('#errorDes').show() , callback(true));
};

/**
 * Kiểm tra số thực hợp lệ và phần nguyên có tối đa 4 kí số.
 */
floatValidator4 = function (value, callback) {
	
	
	// trunk the int part
	var dotIdx = value.indexOf(".");
	
	if (dotIdx > -1) {
		var leftDot = value.substring(0, dotIdx - 1);
	} else {
		leftDot = value
	}
	
	
	if (floatPattern.test(leftDot) && leftDot.length <= 4) {
	  callback(true);
	} else {
	  callback(false);
	}
};


/**
 * Check if value is a positive integer number and within limit if there is limit.
 * @param handsontable - instance of handsontable
 * @param row - cell's row
 * @param prop - cell's property
 * @param newVal - the new value user type in.
 * @param limit - limit of newVal's length
 * @returns
 */
function isInteger (handsontable, row, col, newVal, limit) {
    var valid; 

    if(limit == undefined) {
        valid = (intPattern.test(newVal));
    } else {
    	valid = (intPattern.test(newVal) && newVal.length <= limit);
    }
    
    if (valid) {
    	handsontable.getCellMeta(row, col).valid = true;
        handsontable.render();
        
        return true;
    } else {
    	handsontable.getCellMeta(row, col).valid = false;
        handsontable.render();
        
        return false;
    }
}

/**
 * Check if value is a positive float number and within limit if there is limit.
 * @param handsontable - instance of handsontable
 * @param row - cell's row
 * @param prop - cell's property
 * @param newVal - the new value user type in.
 * @param limit - limit of newVal's length
 * @returns
 */
function isFloat (handsontable, row, col, newVal, limit) {
    var valid; 
    
	// trunk the Integer part
	var dotIdx = newVal.indexOf(".");
	
	if (dotIdx > -1) {
		var leftDot = value.substring(0, dotIdx - 1);
	} else {
		leftDot = newVal;
	}

    if(limit == undefined) {
        valid = (floatPattern.test(leftDot));
    } else {
    	valid = (floatPattern.test(leftDot) && leftDot.length <= limit);
    }
    
    if (valid) {
    	handsontable.getCellMeta(row, col).valid = true;
        handsontable.render();
        
        return true;
    } else {
    	handsontable.getCellMeta(row, col).valid = false;
        handsontable.render();
        
        return false;
    }
}

/**
 * Check if row have at least one column have value but others are empty.
 * @param handsontable - instance of handsontable
 * @returns
 */
function areEmptyColumns (handsontable) {
	var valid = true;
	
    for (var row = 0; row < handsontable.countRows(); row++) {
        if (!handsontable.isEmptyRow(row)) {
            for (var col = 0; col < handsontable.countCols(); col++) { 
                if (handsontable.getDataAtCell(row, col) == null || handsontable.getDataAtCell(row, col) == "") {
                	valid = false;                    
                    handsontable.getCellMeta(row, col).valid = false;
                }          
            }
        }
    }
    
    return valid;
}

/**
 * Check if row with specific column empty or not.
 * @param handsontable
 * @returns
 */
function isEmptyColumn (handsontable, column) {
	var valid = true;
	
    for (var row = 0; row < handsontable.countRows(); row++) {
        if (!handsontable.isEmptyRow(row)) {
            for (var col = 0; col < handsontable.countCols(); col++) { 
            	if (col == column){
	                if (handsontable.getDataAtCell(row, col) == null || handsontable.getDataAtCell(row, col) == "") {
	                	valid = false;                    
	                    handsontable.getCellMeta(row, col).valid = false;
	                } 
            	}
            }
        }
    }
    
    return valid;	
}