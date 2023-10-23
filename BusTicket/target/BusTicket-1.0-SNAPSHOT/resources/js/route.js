/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function loadMyModalEditRoute(endpoint, id) {
    fetch(endpoint, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        let start = document.getElementById('start1');
        let end = document.getElementById('end1');
        let editR = document.getElementById('editR');

        start.value = data[0]["start"];
        end.value = data[0]["end"];
        editR.setAttribute('onclick', `editRoute(${id})`);
    });
}

function editRoute(id) {
    let start = document.getElementById('start1');
    let end = document.getElementById('end1');

    fetch("/BusTicket/api/routes/editRoute", {
        method: 'put',
        body: JSON.stringify({
            "id": id,
            "start": start.value,
            "end": end.value
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

function deleteRoute(endpoint, id) {
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

function getRoutes(endpoint) {
    fetch(endpoint).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("myRoutes");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                h += `
                    <tr>
                        <td>${i + 1}</td>
                        <td>${data[i].start}</td>
                        <td>${data[i].end}</td>
                        <td>
                            <button class="btn btn-primary" onclick="loadMyModalEditRoute('${endpoint + "/getRoute/" + data[i].id}', ${data[i].id})" data-bs-toggle="modal" data-bs-target="#myModalEditRoute">
                               Edit
                            </button>
                            <button class="btn btn-danger" onclick="deleteRoute('${endpoint + "/deleteRoute/" + data[i].id}', ${data[i].id})">
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