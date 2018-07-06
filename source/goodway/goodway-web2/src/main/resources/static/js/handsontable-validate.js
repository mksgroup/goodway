
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
function integerValidator (handsontable, row, col, newVal, limit) {
    var valid; 

    if (newVal) {
	    if (limit == undefined) {
	        valid = (intPattern.test(newVal));
	    } else {
	    	valid = (intPattern.test(newVal) && newVal.length <= limit);
	    }
    } else {
    	valid = true;
    }
    
    if (valid) {
    	handsontable.getCellMeta(row, col).valid = true;
        
    } else {
    	handsontable.getCellMeta(row, col).valid = false;
    }
    
    handsontable.render();
}

/**
 * Check if value is a positive decimal number and within limit if there is limit.
 * @param handsontable - instance of handsontable
 * @param row - cell's row
 * @param prop - cell's property
 * @param newVal - the new value user type in.
 * @param limit - limit of newVal's length
 */
function decimalValidator (handsontable, row, col, newVal, limit) {
    var valid; 
    
	// trunk the Integer part
    if (newVal) {
		var dotIdx = newVal.indexOf(".");
		
		if (dotIdx > -1) {
			var leftDot = newVal.substring(0, dotIdx);
		} else {
			leftDot = newVal;
		}
	
	    if (limit == undefined) {
	        valid = (floatPattern.test(newVal));
	    } else {
	    	valid = (floatPattern.test(newVal) && leftDot.length <= limit);
	    }
    } else {
    	valid = true;
    }
    
    if (valid) {
    	handsontable.getCellMeta(row, col).valid = true;
        
    } else {
    	handsontable.getCellMeta(row, col).valid = false;
    }
    
    handsontable.render();
}

/**
 * Check if text length within litmit (default 500 if undefined) if there is.
 * @param handsontable
 * @param row
 * @param col
 * @param newVal
 * @param limit
 */
function textValidator (handsontable, row, col, newVal, limit) {
    var valid; 

    if (newVal) {
	    if (limit == undefined) {
	        valid = (newVal.length <= 500);
	    } else {
	    	valid = (newVal.length <= limit);
	    }
    } else {
    	valid = true;
    }
    
    if (valid) {
    	handsontable.getCellMeta(row, col).valid = true;
        
    } else {
    	handsontable.getCellMeta(row, col).valid = false;
    }
    
    handsontable.render();	
}

/**
 * Check if row have at least one column have value but others are empty.
 * @param handsontable - instance of handsontable
 * @returns
 */
function areEmptyColumns (handsontable) {
	var inValid = false;
	
    for (var row = 0; row < handsontable.countRows(); row++) {
        if (!handsontable.isEmptyRow(row)) {
            for (var col = 0; col < handsontable.countCols(); col++) { 
                if (handsontable.getDataAtCell(row, col) == null || handsontable.getDataAtCell(row, col) == "") {
                	inValid = true;                    
                    handsontable.getCellMeta(row, col).valid = false;
                }          
            }
        }
    }
    
    handsontable.render();
    
    return inValid;
}

/**
 * Check if row with specific column empty or not.
 * @param handsontable
 * @returns
 */
function isEmptyColumn (handsontable, column) {
	var inValid = false;
	
    for (var row = 0; row < handsontable.countRows(); row++) {
        if (!handsontable.isEmptyRow(row)) {
            for (var col = 0; col < handsontable.countCols(); col++) { 
            	if (col == column){
	                if (handsontable.getDataAtCell(row, col) == null || handsontable.getDataAtCell(row, col) == "") {
	                	inValid = true;                    
	                    handsontable.getCellMeta(row, col).valid = false;
	                } 
            	}
            }
        }
    }
    
    handsontable.render();
    
    return inValid;	
}

/**
 * Check if handsontable table have any invalid cell.
 * @param handsontable
 * @returns
 */
function isValidTable (handsontable) {
	var valid = true;
	
	for (var row = 0; row < handsontable.countRows(); row++){
		if (!handsontable.isEmptyRow(row)) {
			for (var col = 0; col < handsontable.countCols(); col++){
				if (handsontable.getCellMeta(row, col).valid == false) {
					valid = false;
				}
			}
		} else {
			for (var col2 = 0; col2 < handsontable.countCols(); col2++) {
				if (handsontable.getCellMeta(row, col2).valid == false) {
					handsontable.getCellMeta(row, col2).valid = true;
				}
			}
		}
	}
	
	handsontable.render();
	
	return valid;
}

/**
 * Check if handsontable empty or not.
 * @param handsontable
 * @returns
 */
function isEmptyTable (handsontable) {
	
	return (handsontable.countEmptyRows() == handsontable.countRows());
}

/**
 * Check if cell's value within limit or not.
 * use this method if you want to show error alert message at specific column
 * @param handsontable
 * @param row
 * @param col
 * @param value
 * @param limit
 * @returns 
 */
function isWithinLimit (handsontable, row, col, value, limit) {
    var valid; 
    
    if (value) {
	    if (limit == undefined) {
	        valid = (value.length <= 500);
	    } else {
	    	valid = (value.length <= limit);
	    }
    } else {
    	valid = true;
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
 * Check if value is a decimal number,
 * use this method if you want to show error alert message at specific column.
 * @param handsontable
 * @param row
 * @param col
 * @param value
 * @param limit
 * @returns
 */
function isDecimalNumber (handsontable, row, col, value, limit) {
    var valid; 
    
    if (value) { 
    	
		// trunk the Integer part
		var dotIdx = value.indexOf(".");
		
		if (dotIdx > -1) {
			var leftDot = value.substring(0, dotIdx);
		} else {
			leftDot = value;
		}
	
	    if (limit == undefined) {
	        valid = (floatPattern.test(value));
	    } else {
	    	valid = (floatPattern.test(value) && leftDot.length <= limit);
	    }
    } else {
    	valid = true;
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
 * Check if value is a integer number,
 * use this method if you want to show error alert message at specific column.
 * @param handsontable
 * @param row
 * @param col
 * @param value
 * @param limit
 * @returns
 */
function isIntegerNumber (handsontable, row, col, value, limit) {
    var valid; 

    if (value) {
	    if (limit == undefined) {
	        valid = (intPattern.test(value));
	    } else {
	    	valid = (intPattern.test(value) && value.length <= limit);
	    }
    } else {
    	valid = true;
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















