//process payment and save payment
function openPopup() {
    var overlay = document.getElementById("overlay");
    var popup = document.getElementById("popup");

    overlay.style.display = "block";
    popup.style.display = "block";
}

function closePopup(confProceed) {
    let conf;
    if (!confProceed) {
        conf = confirm("Are you sure ?")
    } else {
        conf = confProceed;
    }
    if (conf) {
        var overlay = document.getElementById("overlay");
        var popup = document.getElementById("popup");

        overlay.style.display = "none";
        popup.style.display = "none";
    } else {
        return;
    }
}

const openPaymentForm = (res) => {
    var options = {
        "key": "rzp_test_PPvBvITAnliWl9",
        "amount": res.amount,
        "currency": "INR",
        "name": "Shrishti Rai Major Project",
        "description": "Project Onion Buy",
        "image": "/resources/images/shrishti_rai.jpg",
        "order_id": res.id,
        "handler": function (response) {
            console.log("Payment success response : " + JSON.stringify(response));
        },
        "prefill": {
            "name": "",
            "email": "",
            "contact": ""
        },
        "notes": {
            "address": "HiTech Institute",
        },
        "theme": {
            "color": "#3399cc"
        }
    };
    var rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response) {
        console.log("Payment failed response : " + response);

    })
    rzp1.open();
    ;
}


const savePaymentStatus = (res) => {
    $.ajax({
        url: '/pay/savePaymentSatus',
        data: JSON.stringify({
            amount: toString(res.amount),
            orderEntity: toString(res.entity),
            orderId: res.id,
            status: res.status,
            attempts: toString(res.attempts),
            currency: toString(res.currency)
        }),
        type: "POST",
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            console.log("Payment saved.");
        },
        error: function (error) {
            console.log("Error while saving order.");
        }
    });
};


const initPayment = () => {
    console.log("Payment initiated.")
    let amount = $("#amount").val();
    console.log("Entered amount " + amount)

    //init pay
    $.ajax(
        {
            url: '/pay/processPayment',
            data: JSON.stringify({ amount: amount, info: "payment_details" }),
            contentType: "application/json",
            type: "POST",
            dataType: "json",
            success: function (res) {
                console.log(res)
                if (res.status == "created") {
                    savePaymentStatus(res);
                    openPaymentForm(res);

                }
                console.log("Payment created.")
            },
            error: function (res) {
                console.log("Error while creating order.")
            }
        }
    )
}