function getSelect(){
    let p = document.getElementById("flight");
    return p.options[p.selectedIndex].value;
}

function getGrade(g) {
    document.getElementById("Class").value = g.target.value;
}
function getType(t) {
    document.getElementById("Type").value = t.target.value;

}
function getBookingPrice(p) {
    let nP = 0;
    let type = document.getElementById("Type").innerHTML;
    let grade = document.getElementById("Class").innerHTML;
    let price = parseInt(document.getElementById("price").innerHTML);
    if (grade === "FirstClass") {
        if(type === "Return"){
            nP = (price + 10000)* 2;
        }else {
            nP = (price + 10000);
        }
    }else if(grade === "Business"){
        if(type === "Return"){
            nP = (price + 5000) * 2;
        }else {
            nP = (price + 5000);
        }
    }else {
        if (type === "Return"){
            nP = price * 2;
        }
        nP = price;
    }

}