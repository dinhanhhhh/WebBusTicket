/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var idTicket = 0;

function loadUserEmployee(idUser) {
    fetch(`/BusTicket/api/users/getUser/${idUser}`, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {

        let fullname = document.getElementById('fullnameE');
        let gender = document.getElementById('genderE');
        let address = document.getElementById('addressE');
        let phone = document.getElementById('phoneE');
        let email = document.getElementById('emailE');

        fullname.value = data[0]["fullname"];
        gender.value = data[0]["gender"];
        address.value = data[0]["address"];
        phone.value = data[0]["phone"];
        email.value = data[0]["email"];
    });
}

function loadUser(idUser) {
    fetch(`/BusTicket/api/users/getUser/${idUser}`, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {

        let fullname = document.getElementById('fullnameCustomer');
        let gender = document.getElementById('genderCustomer');
        let address = document.getElementById('addressCustomer');
        let phone = document.getElementById('phoneCustomer');
        let email = document.getElementById('emailCustomer');

        fullname.value = data[0]["fullname"];
        gender.value = data[0]["gender"];
        address.value = data[0]["address"];
        phone.value = data[0]["phone"];
        email.value = data[0]["email"];
    });
}

function addTicket(idTrip, idCustomerLogin) {
    let fullname = document.getElementById('fullnameCustomer');
    let gender = document.getElementById('genderCustomer');
    let address = document.getElementById('addressCustomer');
    let phone = document.getElementById('phoneCustomer');
    let email = document.getElementById('emailCustomer');
    let paymentMethod = document.getElementById('paymentMethod');

    fetch('/BusTicket/api/bookticket', {
        method: 'post',
        body: JSON.stringify({
            "fullname": fullname.value,
            "gender": gender.value,
            "address": address.value,
            "phone": phone.value,
            "email": email.value,
            "idTrip": idTrip,
            "paymentMethod": paymentMethod.value,
            "idCustomerLogin": idCustomerLogin
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        idTicket = `${data.id}`;
        addSeatTicketDetail();
        console.info(data);
        alert('Thành công');
        if (data.idUserLogin === null) {
            location.reload();
        }

        if (data.idUserLogin.userRole === 'ROLE_EMPLOYEE' || data.idUserLogin.userRole === 'ROLE_CUSTOMER') {
            window.open('/BusTicket/tickets');
//            window.location = '/BusTicket/tickets';
        }
        location.reload();
    }).catch(function (err) {
        console.error(err);
//        alert(err);
    });
}
function cancelTicket(ticketId) {
//    fetch(`/BusTicket/api/tickets/${ticketId}`, {
//        method: 'delete'
//    })
   if (confirm("Bạn có chắc chắn muốn xóa?") === true) {
        fetch(`/BusTicket/api/tickets/${ticketId}`, {
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





function getSeats(idCoach) {
    fetch(`/BusTicket/api/seats/${idCoach}`)
            .then(function (res) {
                return res.json();
            }).then(function (data) {
        let d = document.getElementById("loadSeats");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                if (i === 10 || i === 20 || i === 30 || i === 40 || i === 50 || i === 60)
                    h += `
                            <br> <input type="checkbox" value="${data[i].id}" id="seat${data[i].id}}" name="optionSeat"/> ${data[i].name}
                        `;
                else
                    h += `
                            <input type="checkbox" value="${data[i].id}" id="seat${data[i].id}}" name="optionSeat"/> ${data[i].name}
                        `;
            d.innerHTML = h;
        }
    }).catch(function (err) {
        console.error(err);
    });
}

function addSeatTicketDetail() {
    var seatChecked = document.getElementsByName('optionSeat');
    for (var i = 0; i < seatChecked.length; i++) {
        if (seatChecked[i].checked === true) {
            fetch('/BusTicket/api/tickets', {
                method: "post",
                body: JSON.stringify({
                    "idTicket": idTicket,
                    "idSeat": seatChecked[i].value
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(function (res) {
                return res.json();
            }).then(function (data) {
                console.info(data);
            }).catch(function (err) {
                console.error(err);
                alert(err);
            });
        }
    }
}

function getTotalMoney() {

}

function xacNhanTicket(idTicket) {
    fetch(`/BusTicket/api/xacNhanTicket/${idTicket}`, {
        method: 'put'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        location.reload();
        if (data === true)
            alert("Thành công");
        else
            alert("Thất bại");
    });
}

function getTicketUserLogin(idUser) {
    fetch(`/BusTicket/api/ticketdetail/myTicketBook/${idUser}`).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("getMyTicket");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                h += `
                        <tr>
                            <td>${data[i].id}</td>
                            <td>${data[i].idUserLogin.fullname}</td>
                            <td>${data[i].idCustomerNew.fullname}</td>
                            <td>${data[i].idTrip.idRoute.start}</td>
                            <td>${data[i].idTrip.idRoute.end}</td>
                            <td>${moment(data[i].idTrip.startTime).format("DD-MM-YYYY LT")}</td>
                            <td>${data[i].idTrip.idCoach.licensePlates}</td>
                            <td>${moment(data[i].bookDate).format("DD-MM-YYYY LT")}</td>
                            <td>${moment(data[i].paymentDate).format("DD-MM-YYYY LT")}</td>
                            <td>${data[i].paymentMethod}</td>
                            <td>${data[i].statusTicket}</td>
                            <td>
                                <button class="btn btn-primary" onclick="getTicketBookDetail(${data[i].id})" data-bs-toggle="modal" data-bs-target="#myModalXacNhanTicket">
                                   Thông tin vé
                                </button>
                            </td>
                        </tr>
                    `;
            d.innerHTML = h;
        }
    });
}

function getTicketBookByEmployee(idUser) {
    fetch(`/BusTicket/api/ticketdetail/myTicketBook/${idUser}`).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("getMyTicketBookByEmployee");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                h += `
                        <tr>
                            <td>${data[i].id}</td>
                            <td>${data[i].idUserLogin.fullname}</td>
                            <td>${data[i].idCustomerNew.fullname}</td>
                            <td>${data[i].idTrip.idRoute.start}</td>
                            <td>${data[i].idTrip.idRoute.end}</td>
                            <td>${moment(data[i].idTrip.startTime).format("DD-MM-YYYY LT")}</td>
                            <td>${data[i].idTrip.idCoach.licensePlates}</td>
                            <td>${moment(data[i].bookDate).format("DD-MM-YYYY LT")}</td>
                            <td>${moment(data[i].paymentDate).format("DD-MM-YYYY LT")}</td>
                            <td>${data[i].statusTicket}</td>
                            <td>
                                <button class="btn btn-primary" onclick="getTicketBookDetail(${data[i].id})" data-bs-toggle="modal" data-bs-target="#myModalXacNhanTicket">
                                   Xác nhận
                                </button>
                            </td>
                        </tr>
                    `;
            d.innerHTML = h;
        }
    });
}

function getTicketBookDetail(idTicket) {
    fetch(`/BusTicket/api/ticketdetail/getTicketDetail/${idTicket}`).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("getTicketBookDetail");
        let xacNhanButton = document.getElementById("xacNhanButton");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++) {
                h += `
                    <tr>
                        <th>ID</th>
                        <td>${data[i].id}</td>
                    </tr>
                    <tr>
                        <th>Chuyến xe</th>
                        <td>${data[i].idTrip.idRoute.start} - ${data[i].idTrip.idRoute.end}</td>
                    </tr>
                    <tr>
                        <th>Thời gian đi</th>
                        <td>${moment(data[i].idTrip.startTime).format("DD-MM-YYYY LT")}</td>
                    </tr>
                    <tr>
                        <th>Biển số xe</th>
                        <td>${data[i].idTrip.idCoach.licensePlates}</td>
                    </tr>
                    <tr>
                        <th>Tên khách hàng</th>
                        <td>${data[i].idCustomerNew.fullname}</td>
                    </tr>
                    <tr>
                        <th>Địa chỉ</th>
                        <td>${data[i].idCustomerNew.address}</td>
                    </tr>
                    <tr>
                        <th>Số điện thoại</th>
                        <td>${data[i].idCustomerNew.phone}</td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>${data[i].idCustomerNew.email}</td>
                    </tr>
                    <tr>
                        <th>Số ghế</th>
                        <td>
                            <div id="myTicketSeat"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>Ngày đặt</th>
                        <td>${moment(data[i].bookDate).format("DD-MM-YYYY LT")}</td>
                    </tr>
                    <tr>
                        <th>Ngày thanh toán</th>
                        <td>${moment(data[i].paymentDate).format("DD-MM-YYYY LT")}</td>
                    </tr>
                    <tr>
                        <th>Phương thức thanh toán</th>
                        <td>${data[i].paymentMethod}</td>
                    </tr>
                    <tr>
                        <th>Trạng thái</th>
                        <td>${data[i].statusTicket}</td>
                    </tr>`;

                if (data[i].statusTicket === "Ðã thanh toán") {
                    h += `
                        <tr>
                            <th></th>
                            <td>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </td>
                        </tr>`;
                } else {
                    h += `
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-danger" onclick="cancelTicket(${data[i].id})">Hủy vé</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </td>
                        </tr>`;
                }
            }

            d.innerHTML = h;
        }
        xacNhanButton.setAttribute('onclick', `xacNhanTicket(${idTicket})`);
    });

    window.onload(getTicketSeat(idTicket));
}


function getTicketSeat(idTicket) {
    fetch(`/BusTicket/api/ticketdetail/${idTicket}`).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.getElementById("myTicketSeat");
        if (d !== null) {
            let h = "";
            for (let i = 0; i < data.length; i++)
                h += `
                            <h6>${data[i].idSeat.name} - ${(data[i].priceSeat).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'})}</h6>
                        `;

            d.innerHTML = h;
        }
    });
}