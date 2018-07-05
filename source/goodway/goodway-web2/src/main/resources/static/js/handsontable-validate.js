
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

// Methods use with handsontable: beforeChange //

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
        valid = (intPattern.test(newVal)) ? true : false;
    } else {
    	valid = (intPattern.test(newVal) && newVal.length <= limit ) ? true : false;
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

    if(limit == undefined) {
        valid = (floatPattern.test(newVal)) ? true : false;
    } else {
    	valid = (floatPattern.test(newVal) && newVal.length <= limit ) ? true : false;
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
