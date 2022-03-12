let sidoCdNmDom = document.querySelector("#sidoCdNm");
let sgguCdNmDom = document.querySelector("#sgguCdNm");

let setSgguCdNms = (responseParsing) => {
    let sgguCdNmDom = document.querySelector("#sgguCdNm");

    let tag = "<option selected>시군구 선택</option>\n";
    responseParsing.forEach((e) => {
        tag += "<option>"+ e +"</option>\n";
    })

    sgguCdNmDom.innerHTML = tag;
}

let getSgguCdNms = async (sidoCdNm) => {
    let response = await fetch(`http://localhost:8000/api/v1/sggucdnms?sidoCdNm=${sidoCdNm}`);
    let responseParsing = await response.json();
    setSgguCdNms(responseParsing);
}


sidoCdNmDom.addEventListener("change", (event) => {
    let sidoCdNm = event.target.value;
    getSgguCdNms(sidoCdNm);
})



let setHospitals = (responseParsing) => {
    let hospitalsBody = document.querySelector("#hospitalsBody");

    let tag = "\n";
    responseParsing.forEach((e) => {
        console.log(e);
        tag += "<tr>\n";
        tag += "    <td>" + e['yadmNm'] + "</td>\n";
        tag += "    <td>" + e['addr'] + "</td>\n";
        tag += "    <td>" + e['telno'] + "</td>\n";
        tag += "    <td class='text-center'>" + e['rprtWorpClicFndtTgtYn'] + "</td>\n";
        tag += "<tr/>\n"
    })
    tag += '\n';

    hospitalsBody.innerHTML = tag;
}


let getHospitals = async (sidoCdNm, sgguCdNm) => {
    let response = await fetch(`http://localhost:8000/api/v1/hospitals?sidoCdNm=${sidoCdNm}&sgguCdNm=${sgguCdNm}`);
    let responseParsing = await response.json();
    setHospitals(responseParsing);
}

sgguCdNmDom.addEventListener("change", (event) => {

    let sidoCdNm = sidoCdNmDom.options[sidoCdNmDom.selectedIndex].text;
    let sgguCdNm = event.target.value;
    getHospitals(sidoCdNm, sgguCdNm);
})
