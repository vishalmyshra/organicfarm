<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mobile Number Validation with OTP</title>
        <!-- Bootstrap CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="/js/mobilelogin.js" defer></script>
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



    </body>

    </html>