/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//function getCoachByID(endpoint, id) {
//    fetch(endpoint, {
//        method: 'get'
//    }).then(function (res) {
//        return res.json();
//    }).then(function (data) {
//        let name = document.getElementById('name');
//        let totalseat = document.getElementById('totalseat');
//        let licensePlates = document.getElementById('licensePlates');
//
////        let editC = document.getElementById('editC');
////
////        editC.style.display = "block"; //Hien
////        editC.setAttribute('onclick', `editCoach(${id})`);
//
//        name.value = data[0]["name"];
//        totalseat.value = data[0]["totalseat"];
//        licensePlates.value = data[0]["licensePlates"];
//    });
//}

function loadMyModalEditCoach(endpoint, id) {
    fetch(endpoint, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        let name = document.getElementById('name1');
        let totalseat = document.getElementById('totalseat1');
        let licensePlates = document.getElementById('licensePlates1');
        let editC = document.getElementById('editC');

        name.value = data[0]["name"];
        totalseat.value = data[0]["totalseat"];
        licensePlates.value = data[0]["licensePlates"];
        editC.setAttribute('onclick', `editCoach(${id})`);
    });
}

function editCoach(id) {
    let name = document.getElementById('name1');
    let totalseat = document.getElementById('totalseat1');
    let licensePlates = document.getElementById('licensePlates1');

    fetch("/BusTicket/api/coaches/editCoach", {
        method: 'put',
        body: JSON.stringify({
            "id": id,
            "name": name.value,
            "totalseat": totalseat.value,
            "licensePlates": licensePlates.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        location.reload();
        if (data === true) {
            alert('Thành công');
        } else
            alert('Thất bại');
    }).catch(function (err) {
        console.error(err);
    });
}

function deleteCoach(endpoint, id) {
    if (confirm("Bạn có chắc chắn muốn xóa?") === true) {
        fetch(endpoint, {
            method: 'delete'
        }).then(function (res) {
            if (res.status === 204) {
                location.reload();
                alert('Bạn đã xóa thành công');
            }
        }).catch(function (err) {
            console.error(err);
        });
    } else {
        alert('Bạn vẫn chưa muốn xóa!');
    }
}

function getCoaches(endpoint) {
    fetch(endpoint).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("myCoaches");
        let s = document.getElementById('seat');
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                h += `
                    <tr>
                        <td>${i + 1}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].totalseat}</td>
                        <td>${data[i].licensePlates}</td>
                        <td>
                             <button class="btn btn-success" onclick="addSeats(${data[i].id})" style="margin-right: 10px;">Thêm Ghế</button>
                            <button class="btn btn-primary" onclick="loadMyModalEditCoach('${endpoint + "/getCoach/" + data[i].id}', ${data[i].id})" data-bs-toggle="modal" data-bs-target="#myModalEditCoach">
                               Edit
                            </button>
                            <button class="btn btn-danger" onclick="deleteCoach('${endpoint + "/deleteCoach/" + data[i].id}', ${data[i].id})">
                                Delete
                            </button>
                        </td>
                    </tr>

                `;

            d.innerHTML = h;
        }

        let d2 = document.getElementById("mySpinner");
        d2.style.display = "none";
        let d3 = document.getElementById("mySpinner2");
        d3.style.display = "none";
    }).catch(function (err) {
        console.error(err);
    });
}