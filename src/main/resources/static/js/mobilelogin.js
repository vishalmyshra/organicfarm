var otpGenerated = false;
function generateOTP() {
    var userMobileNumber = document.getElementById("mobileNumber").value;
    // Logic to generate OTP
    if (userMobileNumber.trim() == "" || userMobileNumber.length != 10) {
        alert("Please enter a valid mobile number.");
        otpGenerated = false;
        return;
    } else {
        $.ajax({
            url: 'otp/generateotp',
            data: JSON.stringify({
                userMobileNumber: userMobileNumber
            }),
            type: "POST",
            contentType: "application/json",
            success: function (response) {
                //console.log(response + "Response from otp generation");
                // var parsedResponse = JSON.parse(response);
                // var trueUser = parsedResponse.return;
                // console.log("True user " + trueUser);
                // if (trueUser) {
                //loginUserMobile();
                // }
                otpGenerated = true;
                console.log("Otp Generated");
            },
            error: function (error) {
                console.log("Error in generating otp.");
            }
        });
    }
}

function validateOTP() {
    // Logic to validate OTP
    var otp = document.getElementById("otp").value;
    if (!otpGenerated) {
        alert("Generate OTP first");
        return;
    }
    alert('OTP validated successfully!');
    loginUserMobile();
}

function cancelOperation() {
    // Logic to cancel operation
    if (!otpGenerated) {
        alert("Generate OTP first");
        return;
    }
    document.getElementById("otpForm").reset();
    alert('Operation canceled!');
}

function loginUserMobile() {
    var userMobileNumber = document.getElementById("mobileNumber").value;
    $.ajax({
        url: '/usermobilelogin',
        data: {
            mobileNumber: userMobileNumber
        },
        type: "POST",
        success: function (response) {
            console.log(response); // Log the response to see its structure
            try {
                var responseData = JSON.parse(response); // Parse the response
                console.log(responseData);
                if (responseData && responseData.redirectURL) {
                    // Redirect to the received URL
                    console.log("redirecting");
                    redirectToIndex(response.redirectURL);
                } else {
                    console.log("No redirect URL found in the response");
                }
            } catch (error) {
                console.log("Error parsing JSON response:", error);
            }
        },

        error: function (error) {
            console.log("Error Login");
        }
    });
}

const redirectToIndex = (redirectURL) => {
    window.location.href = redirectURL;
}