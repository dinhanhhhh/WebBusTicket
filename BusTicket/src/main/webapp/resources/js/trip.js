/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global moment */

function loadMyModalEditTrip(endpoint, id) {
    fetch(endpoint, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        let name = document.getElementById('name');
        let startTime = document.getElementById('startTime');
        let price = document.getElementById('price');
        let route = document.getElementById('route');
        let driver = document.getElementById('driver');
        let coach = document.getElementById('coach');
        let editT = document.getElementById('editT');

        name.value = data[0]["name"];
        startTime.value = moment().format("YYYY-MM-DD HH:mm:ss");
        price.value = data[0]["price"];
        route.value = data[0]["idRoute"]["id"];
        driver.value = data[0]["idDriver"]["id"];
        coach.value = data[0]["idCoach"]["id"];
        editT.setAttribute('onclick', `editTrip(${id})`);
    });
}

function editTrip(id) {
    let name = document.getElementById('name');
    let startTime = document.getElementById('startTime');
    let price = document.getElementById('price');
    let route = document.getElementById('route');
    let driver = document.getElementById('driver');
    let coach = document.getElementById('coach');

    fetch("/BusTicket/api/trips/editTrip", {
        method: 'put',
        body: JSON.stringify({
            "id": id,
            "name": name.value,
            "startTime": startTime.value,
            "price": price.value,
            "idRoute": route.value,
            "idDriver": driver.value,
            "idCoach": coach.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        location.reload();
        if (data === true)
            alert('Thành công');
        else
            alert('Thất bại');
    }).catch(function (err) {
        console.error(err);
    });
}

function deleteTrip(endpoint, id) {
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

function loadAdminTrips(endpoint) {
    fetch(endpoint).then(function (res) {
        return res.json();
    }).then(function (data) {
        console.info(data);
        let msg = "";
        for (let i = 0; i < data.length; i++) {
            msg += `
                    <tr>
                        <td>${i + 1}</td>
                        <td> 
                            <img src="${data[i].image}" alt="${data[i].name}" width='120'/>
                        </td>
                        <td>${data[i].name} </td>
                        <td>${data[i].idRoute.start}</td>
                        <td>${data[i].idRoute.end}</td>
                        <td>${moment(data[i].startTime).format("DD-MM-YYYY LT")}</td>
                        <td>${data[i].price}</td>
                        <td>${data[i].idDriver.fullname} - ${data[i].idDriver.userRole}</td>
                        <td>${data[i].idCoach.licensePlates}</td>
                        <td>
                            <button class="btn btn-primary" onclick="loadMyModalEditTrip('${endpoint + "/getTrip/" + data[i].id}', ${data[i].id})" data-bs-toggle="modal" data-bs-target="#myModalEditTrip">
                               Edit
                            </button>
                            <button class="btn btn-danger" onclick="deleteTrip('${endpoint + "/deleteTrip/" + data[i].id}', ${data[i].id})"> 
                                Delete
                            </button>
                        </td>
                    </tr> 
                    `;
        }

        let d = document.getElementById("adminTrip");
        d.innerHTML = msg;

        let d2 = document.getElementById("myLoading");
        d2.style.display = "none";
    });
}

function loadFeedback(endpoint) {
    fetch(endpoint).then(function (res) {
        return res.json();
    }).then(function (data) {
        let b = document.getElementById("feedbacks");
        let h = ``;
        for (let d of data)
            h += `
                <li>
                     <div class="container boxReview-comment-item mb-4 form-control">
                        <div>
                            <img src="${d.user.avatar}" alt="Logo" class="rounded-circle" style="width:25px; height: 25px;">
                        </div>
                        <div class="boxReview-comment-item-title is-flex is-justify-content-space-between is-align-items-center">
                            <div class="is-flex is-align-items-center">
                                <span class="name fw-bold">${d.user.fullname}</span>
                            </div>
                            <p class="date-time"><b>${moment(d.createdDate).locale("vi").fromNow()}</b> - ${moment(d.createdDate).locale("vi").format('lll')}</p>
                        </div> 
                        <div class="boxReview-comment-item-review p-2">
                            <div class="item-review-comment my-1 is-flex is-justify-content-space-between is-flex-direction-column">
                                <div class="comment-content">
                                    <p>${d.comment}</p>
                                </div>
                                <div class="comment-image is-flex">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            `;
        b.innerHTML = h;

    });
}

function addFeedback(endpoint, idChuyenDi, xacNhan, thanhCong, thatBai) {
    if (confirm(xacNhan) === true) {
        fetch(endpoint, {
            method: "post",
            body: JSON.stringify({
                "comment": document.getElementById("comment").value,
                "idTrip": idChuyenDi
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (data) {
            if (data) {
                alert(thanhCong);
                let d = document.getElementById("feedbacks");

                let h = `
                     <li>
                        <div class="container boxReview-comment-item mb-4 form-control">
                             <div>
                                 <img src="${data.user.avatar}" alt="Logo" class="rounded-circle" style="width:25px; height: 25px;">
                             </div>
                             <div class="boxReview-comment-item-title is-flex is-justify-content-space-between is-align-items-center">
                                 <div class="is-flex is-align-items-center">
                                     <span class="name fw-bold">${data.user.fullname}</span>
                                 </div>
                                 <p class="date-time"><b>${moment(data.createdDate).locale("vi").fromNow()}</b> - ${moment(data.createdDate).locale("vi").format('lll')}</p>
                             </div> 
                             <div class="boxReview-comment-item-review p-2">
                                 <div class="item-review-comment my-1 is-flex is-justify-content-space-between is-flex-direction-column">
                                     <div class="comment-content">
                                         <p>${data.comment}</p>
                                     </div>
                                     <div class="comment-image is-flex">
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </li>
                    `;
                d.insertAdjacentHTML("beforebegin", h);
            } else {
                alert(thatBai);
            }
            location.reload();
        });
    }

}