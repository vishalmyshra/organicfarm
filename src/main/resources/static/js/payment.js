const overlay = document.getElementById("overlay");
const popup = document.getElementById("popup");

const openPopup = () => {
    overlay.style.display = "block";
    popup.style.display = "block";
};

const closePopup = (confProceed) => {
    const conf = confProceed ? confProceed : confirm("Are you sure ?");
    if (conf) {
        overlay.style.display = "none";
        popup.style.display = "none";
    } else {
        return;
    }
};

const openPaymentForm = (res) => {
    const options = {
        key: "rzp_test_PPvBvITAnliWl9",
        amount: res.amount,
        currency: "INR",
        name: "Shrishti Rai Major Project",
        description: "Project Onion Buy",
        image: "/resources/images/shrishti_rai.jpg",
        order_id: res.id,
        handler: function (response) {
            console.log("Payment success response: " + JSON.stringify(response));
        },
        prefill: {
            name: "",
            email: "",
            contact: ""
        },
        notes: {
            address: "HiTech Institute",
        },
        theme: {
            color: "#3399cc"
        }
    };
    const rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response) {
        console.log("Payment failed response: " + response);
    });
    rzp1.open();
};

const savePaymentStatus = (res) => {
    $.ajax({
        url: '/pay/savePaymentSatus',
        data: JSON.stringify({
            amount: res.amount.toString(),
            orderEntity: res.entity.toString(),
            orderId: res.id,
            status: res.status,
            attempts: res.attempts.toString(),
            currency: res.currency.toString()
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
    console.log("Payment initiated.");
    const amount = $("#amount").val();
    console.log("Entered amount: " + amount);

    $.ajax({
        url: '/pay/processPayment',
        data: JSON.stringify({ amount: amount, info: "payment_details" }),
        contentType: "application/json",
        type: "POST",
        dataType: "json",
        success: function (res) {
            console.log(res);
            if (res.status === "created") {
                savePaymentStatus(res);
                openPaymentForm(res);
            }
            console.log("Payment created.");
        },
        error: function (res) {
            console.log("Error while creating order.");
        }
    });
};


//hadleRedirection to html pages

const redirectToOtpLoginController = () => {
    window.location.href = "usermobileloginpage";
}