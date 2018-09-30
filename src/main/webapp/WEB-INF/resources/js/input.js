// version: beta
// created: 2005-08-30
// updated: 2005-08-31
// mredkj.com

// Listen for input event on numInput.
function nonnumber(event) {
    return (event.charCode === 8 || event.charCode === 0) ? null : event.charCode >= 48 && event.charCode <= 57 ;
};

function checkValue0(number, event, value) {
    var index = number.value.toString();
    if(parseInt() > value || event.charCode === 8 || event.charCode === 0){
        return event.charCode === 5;
    } 
    if(number.value === ""){
        return  event.charCode === 48 || event.charCode === 49;
    }
    if(number.value === "0" || number.value === "1"){
        return  event.charCode === 46;
    }
    if(index.indexOf(".")>-1) {
        return  event.charCode >= 48 && event.charCode <= 57;    
    }
    else{
        return  event.charCode >= 48 && event.charCode <= 57 || event.charCode === 46;
    }
};

function checkValue(number, event, value) {
    if(number.value ===""){
        return  event.charCode >= 48 && event.charCode <= 57;
    }
    return (number.value > value || event.charCode === 8 || event.charCode === 0) ? event.charCode===5 :  event.charCode >= 48 && event.charCode <= 57 || event.charCode === 46;
};

function checkValueDec(number, event, value) {
    var index = number.value.toString();
//    document.getElementById('text').innerHTML = index + " " + index.indexOf(index);
    if(number.value > value || event.charCode === 8 || event.charCode === 0){
        return event.charCode===5;
    } 
    if(number.value ===""){
        return  event.charCode >= 49 && event.charCode <= 57;
    }
    else if(index.indexOf(".")>-1) {
        return  event.charCode >= 48 && event.charCode <= 57;    
    }else{
        return  event.charCode >= 48 && event.charCode <= 57 || event.charCode === 46;
    }
};

function isNumberKey(number, event){
    var index = number.value;
    document.getElementById('text').innerHTML = index + " " + index.toString().indexOf(index);
    if(index==="." || index===0) {
        return  event.charCode >= 49 && event.charCode <= 57;
    }
}

function clear0(thefield){
    if (thefield.value === 0) thefield.value = "";
}
function clear0d0(thefield){
    if (thefield.value === 0 || thefield.value === 0.0) thefield.value = "";
}

function getValue(field){
    if (field.value === " ") field.value = 0;
}    

