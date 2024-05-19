<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mobile Number Validation with OTP</title>
        <!-- Bootstrap CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            Mobile Number Validation with OTP
                        </div>
                        <div class="card-body">
                            <form id="otpForm">
                                <div class="form-group">
                                    <label for="mobileNumber">Mobile Number</label>
                                    <input type="text" class="form-control" id="mobileNumber" name="mobileNumber"
                                        placeholder="Enter mobile number" required>
                                </div>
                                <div class="form-group">
                                    <label for="otp">OTP</label>
                                    <input type="text" class="form-control" id="otp" placeholder="Enter OTP">
                                </div>
                                <button type="button" class="btn btn-primary" onclick="generateOTP()">Generate
                                    OTP</button>
                                <button type="button" class="btn btn-success" onclick="validateOTP()">Validate
                                    OTP</button>
                                <button type="button" class="btn btn-secondary"
                                    onclick="cancelOperation()">Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and jQuery -->

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
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
                var userMobileNumber = "9555512824";
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

        </script>

    </body>

    </html>