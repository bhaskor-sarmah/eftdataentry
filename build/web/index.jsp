<%-- 
    Document   : index
    Created on : Jan 29, 2020, 12:31:30 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="./fragments/styles.jsp" />
    </head>
    <body>
        <div class="loader"></div>
        <%--<jsp:include page="./fragments/header.jsp" />--%>
        <div class="container-fluid" style="height:770px;overflow: scroll;">
            <div class="row" style="margin-top:5px;font-size: small;">
                <div class="col-1">
                    <p><a href="./Logout" class="btn btn-sm btn-danger" tabindex="-1">Logout</a></p>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                    <p>
                    <div class="form-group">
                        <label for="docType"><b>IMAGE TYPE</b></label>
                        <select class="form-control" name="docType" id="docType" style="font-size: small;width: 100px;" onchange="doChangeSelect(this.value);">
                            <option value="0">--SELECT--</option>
                            <option value="orderSheet">Order, Notice, SP Ref</option>
                            <option value="form">Form I & II</option>
                            <option value="address">Address</option>
                            <option value="relatives">Relatives</option>
                        </select>
                    </div>
                    </p>
                </div>
                <div class="col-10" id="imageDiv" style="height: 320px;overflow-y: scroll;" tabindex="-1">

                </div>
                <div class="col-1">
                    <p><a href="#" class="exit btn btn-sm btn-info" tabindex="-1">Exit Fullscreen</a></p>
                    <p><a href="#" class="open btn btn-sm btn-info" tabindex="-1">Full Screen</a></p>
				<p><a href="#" class="btn btn-sm btn-info" tabindex="-1" onclick="nextImage()">Next Image</a></p>
                    <p><a href="#" class="btn btn-sm btn-info" tabindex="-1" onclick="prevImage()">Prev Image</a></p>
                    <p><a href="#" class="btn btn-sm btn-success" tabindex="-1" id="submitBtn" onclick="doSubmitForm()">Submit Data</a></p>
                </div>
            </div>
            <div class="row" style="border-top: 1px solid grey;margin-bottom: 5px;"></div>
            <form action="./SaveData" method="POST" id="suspectForm">
                <div class="card entryCard" style="font-size: small;font-weight: bold;" id="orderCard">
                    <div class="card-body" style="padding-top: 3px;padding-bottom: 3px">
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Order & Notice Details</div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="firstOrderDate"><span class="mandatory">*&nbsp;</span>Present FT First Order Date :</label>
                                    <input type="text" class="form-control date" name="firstOrderDate" id="firstOrderDate" placeholder="DD/MM/YYYY"/>
                                    <span class="errorTxt" id="firstOrderDateError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="lastOrderDate"><span class="mandatory">*&nbsp;</span>Last Order Date</label>
                                    <input type="text" class="form-control date"  name="lastOrderDate" id="lastOrderDate"  placeholder="DD/MM/YYYY"/>
                                    <span class="errorTxt" id="lastOrderDateError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="ftCaseNo"><span class="mandatory">*&nbsp;</span>FT Case No :</label>
                                    <input type="text" class="form-control" name="ftCaseNo" id="ftCaseNo" placeholder="Enter FT Case No"/>
                                    <span class="errorTxt" id="ftCaseNoError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="noticeIssueDate"><span class="mandatory">*&nbsp;</span>Notice issue date:</label>
                                    <input type="text" class="form-control" name="noticeIssueDate" id="noticeIssueDate" placeholder="DD/MM/YYYY"/>
                                    <span class="errorTxt" id="noticeIssueDateError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="hearingDate"><span class="mandatory">*&nbsp;</span>Hearing date:</label>
                                    <input type="text" class="form-control" name="hearingDate" id="hearingDate" placeholder="DD/MM/YYYY"/>
                                    <span class="errorTxt" id="hearingDateError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="noticeThana">Notice Thana Name :</label>
                                    <input type="text" class="form-control" name="noticeThana" id="noticeThana" placeholder="Enter Thana Name"/>
                                    <span class="errorTxt" id="noticeThanaError"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="noticeType"><span class="mandatory">*&nbsp;</span>Notice Type:</label>
                                    <select name="noticeType" id="noticeType" class="form-control">
                                        <option value="---" selected>--SELECT--</option>
                                        <option value="BY HAND">BY HAND</option>
                                        <option value="LATKAJARI">LATKAJARI</option>
                                    </select>
                                    <span class="errorTxt" id="noticeTypeError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="finalOrderDate">Final Order date:</label>
                                    <input type="text" class="form-control" name="finalOrderDate" id="finalOrderDate" placeholder="DD/MM/YYYY"/>
                                    <span class="errorTxt" id="finalOrderDateError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="opinionType">Final Opinion:</label>
                                    <select name="opinionType" id="opinionType" class="form-control">
                                        <option value="---" selected>--SELECT--</option>
                                        <option value="FOREIGNER">FOREIGNER</option>
                                        <option value="NOT-FOREIGNER">NOT-FOREIGNER</option>
                                        <option value="EX-PARTE">EX-PARTE</option>
                                    </select>
                                    <span class="errorTxt" id="opinionTypeError"></span>
                                </div>
                            </div>
                            <div class="col"></div>
                            <div class="col"></div>
                            <div class="col"></div>
                        </div>
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">SP Reference Details</div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="spCaseNo"><span class="errorTxt">*&nbsp;</span>SP Ref. No. :</label>
                                    <input type="text" class="form-control" name="spCaseNo" id="spCaseNo" placeholder="Enter SP Ref. No."/>
                                    <span class="errorTxt" id="spCaseNoError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="imdtNo"><span class="errorTxt">*&nbsp;</span>IMDT No. :</label>
                                    <input type="text" class="form-control" name="imdtNo" id="imdtNo" placeholder="Enter IMDT No."/>
                                    <span class="errorTxt" id="imdtNoError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="spStateName"><span class="errorTxt">*&nbsp;</span>State Name :</label>
                                    <input type="text" class="form-control" name="spStateName" id="spStateName" placeholder="Enter State Name" value="ASSAM" disabled="disabled"/>
                                    <span class="errorTxt" id="spStateNameError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="spDistName"><span class="errorTxt">*&nbsp;</span>District Name :</label>
                                    <select name="spDistName" id="spDistName" class="form-control" onchange="doChangeDist(this.value)">
                                        <option value="---" selected>--SELECT--</option>
                                        <c:forEach var="obj" items="${distList}">
                                            <option value="${obj.code}">${obj.name}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="errorTxt" id="spDistNameError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="spPsName"><span class="errorTxt">*&nbsp;</span>Police Station Name:</label>
                                    <select name="spPsName" id="spPsName" class="form-control"+>
                                        <!--<select name="spPsName" id="spPsName" class="form-control" onchange="doChangeThana(this.value)">-->
                                        <option value="---" selected>--SELECT--</option>
                                    </select>
                                    <span class="errorTxt" id="spPsNameError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="spVillageName">Village Name:</label>
                                    <input type="text" class="form-control" name="spVillageName" id="spVillageName" placeholder="Enter Village Name"/>
                                    <!--                                    <select name="spVillageName" id="spVillageName" class="form-control">
                                                                            <option value="---" selected>--SELECT--</option>
                                                                        </select>-->
                                    <span class="errorTxt" id="spVillageNameError"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card entryCard" style="font-size: small;font-weight: bold;" id="formCard">
                    <div class="card-body" style="padding-top: 3px;padding-bottom: 3px">
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Suspect Details</div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="suspectName"><span class="errorTxt">*&nbsp;</span>Suspect Name</label>
                                <input type="text" name="suspectName" id="suspectName" class="form-control" placeholder="Enter Name"/>
                                <span class="errorTxt" id="suspectNameError"></span>
                            </div>
                            <div class="col">
                                <label for="fatherName">Father's Name</label>
                                <input type="text" name="fatherName" id="fatherName" class="form-control"  placeholder="Enter Father's Name"/>
                                <span class="errorTxt" id="fatherNameError"></span>
                            </div>
                            <div class="col">
                                <label for="motherName">Mother's Name</label>
                                <input type="text" name="motherName" id="motherName" class="form-control"  placeholder="Enter Mother's Name"/>
                                <span class="errorTxt" id="motherNameError"></span>
                            </div>
                            <div class="col">
                                <label for="maritalStatus">Marital Status</label>
                                <select id="maritalStatus" name="maritalStatus" class="form-control" >
                                    <option value="--">--SELECT--</option>
                                    <option value="01">UNMARRIED</option>
                                    <option value="02">MARRIED</option>
                                    <option value="03">DIVORCED</option>
                                    <option value="04">WIDOW</option>
                                    <option value="05">SEPARATED</option>
                                </select>
                                <span class="errorTxt" id="maritalStatusError"></span>
                            </div>
                            <div class="col">
                                <label for="spouseName">Spouse Name</label>
                                <input type="text" name="spouseName" id="spouseName" class="form-control"  placeholder="Enter Spouse Name"/>
                                <span class="errorTxt" id="spouseNameError"></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="age"><span class="errorTxt">*&nbsp;</span>Age</label>
                                <input type="text" name="age" id="age" class="form-control"  placeholder="Enter Age"/>
                                <span class="errorTxt" id="ageError"></span>
                            </div>
                            <div class="col">
                                <label for="dob">Date Of Birth</label>
                                <input type="text" name="dob" id="dob" class="form-control"  placeholder="DD/MM/YYYY"/>
                                <span class="errorTxt" id="dobError"></span>
                            </div>
                            <div class="col">
                                <label for="gender"><span class="errorTxt">*&nbsp;</span>Gender</label>
                                <select id="gender" name="gender" class="form-control" >
                                    <option value="---">--SELECT--</option>
                                    <option value="01">MALE</option>
                                    <option value="02">FEMALE</option>
                                    <option value="03">OTHERS</option>
                                </select>
                                <span class="errorTxt" id="genderError"></span>
                            </div>
                            <div class="col">
                                <label for="mobile">Mobile No.</label>
                                <input type="text" name="mobile" id="mobile" class="form-control"  placeholder="Enter Mobile"/>
                                <span class="errorTxt" id="mobileError"></span>
                            </div>
                            <div class="col">
                                <label for="placeOfBirth">Place of Birth</label>
                                <input type="text" name="placeOfBirth" id="placeOfBirth" class="form-control"  placeholder="Enter Place of Birth"/>
                                <span class="errorTxt" id="placeOfBirthError"></span>
                            </div>
                            <div class="col">
                                <label for="occupation">Profession/Occupation</label>
                                <input type="text" name="occupation" id="occupation" class="form-control"  placeholder="Occupation (If Any)"/>
                                <span class="errorTxt" id="occupationError"></span>
                            </div>
                        </div>
                        <div class="row bg-info text-white" style="margin-top: 15px;">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Physical Description</div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="height">Height :</label>
                                    <input type="text" class="form-control" name="height" id="height" placeholder="Enter Height"/>
                                    <span class="errorTxt" id="heightError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="hair">Hair Color:</label>
                                    <input type="text" class="form-control" name="hair" id="hair" placeholder="Enter Hair Color"/>
                                    <span class="errorTxt" id="hairError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="eye">Eye Color:</label>
                                    <input type="text" class="form-control" name="eye" id="eye" placeholder="Enter Eye Color"/>
                                    <span class="errorTxt" id="eyeError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="complexion">Complexion :</label>
                                    <input type="text" class="form-control" name="complexion" id="complexion" placeholder="Complexion"/>
                                    <span class="errorTxt" id="complexionError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="identificationMark">Identification Mark :</label>
                                    <input type="text" class="form-control" name="identificationMark" id="identificationMark" placeholder="Enter Identification Mark"/>
                                    <span class="errorTxt" id="identificationMarkError"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card entryCard" style="font-size: small;font-weight: bold;" id="relCard">
                    <div class="card-body" id="moreMember"  style="padding-top: 3px;padding-bottom: 3px;height:250px;overflow-y:scroll;margin-bottom:20px;">
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Relative Details</div>
                        </div>
                    </div>
                    <div class="card-footer text-center">
                        <button type="button" name="btnAddMore" class="btn btn-info" id="btnAddMore">
                            <i class="fa fa-plus"></i>
                        </button>
                        <button type="button" name="btnRemove" class="btn btn-danger" id="btnRemove">
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="card entryCard" style="font-size: small;font-weight: bold;" id="addCard">
                    <div class="card-body" style="padding-top: 3px;padding-bottom: 3px">
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Address in Country of Origin</div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="villageOrigin">Village :</label>
                                    <input type="text" class="form-control" name="villageOrigin" id="villageOrigin" placeholder="Enter Village"/>
                                    <span class="errorTxt" id="villageOriginError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="psOrigin">Police Station :</label>
                                    <input type="text" class="form-control" name="psOrigin" id="psOrigin" placeholder="Enter P.S."/>
                                    <span class="errorTxt" id="psOriginError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="districtOrigin">District :</label>
                                    <input type="text" class="form-control" name="districtOrigin" id="districtOrigin" placeholder="Enter District"/>
                                    <span class="errorTxt" id="districtOriginError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="stateOrigin">State :</label>
                                    <input type="text" class="form-control" name="stateOrigin" id="stateOrigin" placeholder="Enter State"/>
                                    <span class="errorTxt" id="stateOriginError"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="countryOrigin">Country :</label>
                                    <input type="text" class="form-control" name="countryOrigin" id="countryOrigin" placeholder="Enter Country"/>
                                    <span class="errorTxt" id="countryOriginError"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row bg-info text-white">
                            <div class="col" style="font-size: small;font-weight: bold; text-decoration: underline;margin-top: 5px;">Address in India</div>
                        </div>
                        <div class="row">
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="village">Village :</label>
                                    <input type="text" class="form-control" name="village" id="village" placeholder="Enter Village"/>
                                    <span class="errorTxt" id="villageError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="postOffice">Post Office :</label>
                                    <input type="text" class="form-control" name="postOffice" id="postOffice" placeholder="Post Office"/>
                                    <span class="errorTxt" id="postOfficeError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="revVillage">Revenue Village :</label>
                                    <input type="text" class="form-control" name="revVillage" id="revVillage" placeholder="Enter Revenue Village"/>
                                    <span class="errorTxt" id="revVillageError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="policeStation">Police Station :</label>
                                    <input type="text" class="form-control" name="policeStation" id="policeStation" placeholder="Enter Police Station"/>
                                    <span class="errorTxt" id="policeStationError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="district">District :</label>
                                    <input type="text" class="form-control" name="district" id="district" placeholder="Enter District"/>
                                    <span class="errorTxt" id="districtError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="state">State :</label>
                                    <input type="text" class="form-control" name="state" id="state" placeholder="Enter State"/>
                                    <span class="errorTxt" id="stateError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="pin">Pin Code :</label>
                                    <input type="text" class="form-control" name="pin" id="pin" placeholder="Enter Pin Code"/>
                                    <span class="errorTxt" id="pinError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="lat">Latitude :</label>
                                    <input type="text" class="form-control" name="lat" id="lat" placeholder="Enter Latitude"/>
                                    <span class="errorTxt" id="latError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="lon">Longitude :</label>
                                    <input type="text" class="form-control" name="lon" id="lon" placeholder="Enter Longitude"/>
                                    <span class="errorTxt" id="lonError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="villHead">Village Head :</label>
                                    <input type="text" class="form-control" name="villHead" id="villHead" placeholder="Enter Village Head"/>
                                    <span class="errorTxt" id="villHeadError"></span>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label for="villHeadPh">Village Head Ph:</label>
                                    <input type="text" class="form-control" name="villHeadPh" id="villHeadPh" placeholder="Enter Village Head Ph"/>
                                    <span class="errorTxt" id="villHeadPhError"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="folder" id="folder" value="" />
            </form>
        </div>
    </div>
    <%--<jsp:include page="./fragments/footer.jsp" />--%>
    <jsp:include page="./fragments/scripts.jsp" />
    <script type="text/javascript" src="./fullscreenJs/jquery.fullscreen.min.js"></script>
    <script type="text/javascript" src="./js/imageHandling.js"></script>
    <script type="text/javascript" src="./js/custom.js?v=12392823"></script>
    <script type="text/javascript">
                                        var imageList = [];
                                        var memberCount = 0;
                                        function doChangeSelect(str) {
                                            $(".loader").show();
                                            $(".entryCard").hide();
                                            switch (str) {
                                                case "orderSheet" :
                                                    $("#orderCard").show();
                                                    break;
//                                case "spRef" :
//                                    $("#spRefCard").show();
//                                    break;
                                                case "form" :
                                                    $("#formCard").show();
                                                    break;
                                                case "address" :
                                                    $("#addCard").show();
                                                    break;
                                                case "relatives" :
                                                    $("#relCard").show();
                                                    break;
//                                case "notice" :
//                                    $("#noticeCard").show();
//                                    break;
                                                default:
                                                    break;
                                            }
                                            $(".loader").fadeOut();
                                        }

                                        $(function () {
                                            $('.open').click(function () {
                                                $('body').fullscreen();
                                                return false;
                                            });
                                            $('.exit').click(function () {
                                                $.fullscreen.exit();
                                                return false;
                                            });
                                            var x = screen.height / 2 - 50;
                                            console.log(x);
                                            //$("#imageDiv").css("height", Math.round(x) + 'px');
                                            var i = 0;
        <c:forEach var="obj" items="${imgList}">
                                            imageList[i++] = {
                                                id: ${obj.id},
                                                path: "${obj.path}",
                                                folder: "${obj.folder}"
                                            };
        </c:forEach>
                                            $("#folder").val(imageList[0].folder);
                                            showImage();
                                            //document.onmousedown = startDrag;
                                            //document.onmouseup = stopDrag;
                                            //doDatePicker();

                                            //$("#btnAddMore").click();;

                                            $("#docType").val("0");
                                            $(".entryCard").hide();
                                            $("#docType").focus();
                                        });
                                        $("#btnAddMore").click(function () {
                                            memberCount++;
                                            var str = "<fieldset class = \"scheduler-border\" id=\"memberSpan_" + memberCount + "\" style=\"margin-bottom: 0px;padding-bottom: 0px;\">\n" +
                                                    "                            <legend class=\"scheduler-border\">Member No - " + memberCount + "</legend>\n" +
                                                    "                            <div class=\"row\" style=\"margin-bottom: 0px;\">\n" +
                                                    "                                <div class=\"col\">\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <label for=\"memberName\"><span class=\"errorTxt\">*&nbsp;</span>Name :</label>\n" +
                                                    "                                        <input type=\"text\" name=\"memberName\" id=\"memberName_" + memberCount + "\" class=\"form-control\" placeholder=\"Enter Name\">\n" +
                                                    "                                        <span class=\"errorTxt\" id=\"memberName_" + memberCount + "_Error\"></span>\n" +
                                                    "                                    </div>\n" +
                                                    "                                </div>\n" +
                                                    "                                <div class=\"col\">\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <label for=\"memberRel\"><span class=\"errorTxt\">*&nbsp;</span>Relation :</label>\n" +
                                                    "                                        <select name=\"memberRel\" id=\"memberRel_" + memberCount + "\" class=\"form-control\">\n" +
                                                    "                                            <option value=\"---\">--SELECT--</option>\n" +
                                                    "                                            <option value=\"01\">SPOUSE</option>\n" +
                                                    "                                            <option value=\"02\">SON</option>\n" +
                                                    "                                            <option value=\"03\">DAUGHTER</option>\n" +
                                                    "                                            <option value=\"04\">BROTHER</option>\n" +
                                                    "                                            <option value=\"05\">SISTER</option>\n" +
                                                    "                                            <option value=\"06\">DEPENDENT</option>\n" +
                                                    "                                            <option value=\"07\">GRAND-SON</option>\n" +
                                                    "                                            <option value=\"08\">GRAND-DAUGHTER</option>\n" +
                                                    "                                            <option value=\"09\">FATHER</option>\n" +
                                                    "                                            <option value=\"10\">MOTHER</option>\n" +
                                                    "                                        </select>\n" +
                                                    "                                        <span class=\"errorTxt\" id=\"memberRel_" + memberCount + "_Error\"></span>\n" +
                                                    "                                    </div>\n" +
                                                    "                                </div>\n" +
                                                    "                                <div class=\"col\">\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <label for=\"memberAge\">Member Age :</label>\n" +
                                                    "                                        <input type=\"text\" name=\"memberAge\" id=\"memberAge_" + memberCount + "\" class=\"form-control\" placeholder=\"Enter Age\">\n" +
                                                    "                                        <span class=\"errorTxt\" id=\"memberAge_" + memberCount + "_Error\"></span>\n" +
                                                    "                                    </div>\n" +
                                                    "                                </div>\n" +
                                                    "                                <div class=\"col\">\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <label for=\"memDob\">Date of Birth :</label>\n" +
                                                    "                                        <input type=\"text\" name=\"memDob\" id=\"memDob_" + memberCount + "\" class=\"form-control\"  placeholder=\"DD/MM/YYYY\">\n" +
                                                    "                                        <span class=\"errorTxt\" id=\"memDob_" + memberCount + "_Error\"></span>\n" +
                                                    "                                    </div>\n" +
                                                    "                                </div>\n" +
                                                    "                                <div class=\"col\">\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <label for=\"memPob\">Place of Birth :</label>\n" +
                                                    "                                        <input type=\"text\" name=\"memPob\" id=\"memPob_" + memberCount + "\" class=\"form-control\"  placeholder=\"Enter Place of Birth\">\n" +
                                                    "                                        <span class=\"errorTxt\" id=\"memPob_" + memberCount + "_Error\"></span>\n" +
                                                    "                                    </div>\n" +
                                                    "                                </div>\n" +
                                                    "                            </div>\n" +
                                                    "                        </fieldset>";
                                            $("#moreMember").append(str);
                                            $("#memberName_" + memberCount).focus();
                                        });
                                        $("#btnRemove").click(function () {
                                            if (memberCount <= 0) {
                                                alert("No Member Present!");
                                            } else {
                                                $("#memberSpan_" + memberCount--).remove();
                                            }
                                        });

//                                        function doSubmitForm() {
//                                            if (confirm("Are you sure you want to submit the form ?")) {
//                                                if (doValidateForm()) {
//                                                    //console.log(console.log($("#suspectForm").serializeArray()));
//                                                }
//                                            }
//                                        }

                                        function doChangeDist(str) {
                                            $.ajax({
                                                type: "POST",
                                                url: 'GetThanaList',
                                                async: false,
                                                data: 'dist=' + str,
                                                success: function (data) {
                                                    $("#spPsName").html(data);
                                                },
                                                error: function (x, t, m) {
                                                    if (t === "timeout") {
                                                        alert("Error Getting Circle List. Request Timeout. Please Try Again");
                                                    } else {
                                                        alert("Error Getting Circle List. Error: " + t + ".Please Try Again");
                                                    }
                                                }
                                            });
                                        }

                                        function doChangeThana(str) {
                                            $.ajax({
                                                type: "POST",
                                                url: 'GetVillageList',
                                                async: false,
                                                data: 'thana=' + str,
                                                success: function (data) {
                                                    $("#spVillageName").html(data);
                                                },
                                                error: function (x, t, m) {
                                                    if (t === "timeout") {
                                                        alert("Error Getting Circle List. Request Timeout. Please Try Again");
                                                    } else {
                                                        alert("Error Getting Circle List. Error: " + t + ".Please Try Again");
                                                    }
                                                }
                                            });
                                        }

//                                        $('.date').on('keypress', function (e) {
//                                            var key = e.charCode || e.keyCode || 0;
//                                            if (key === 8 || // Backspace
//                                                    (key >= 48 && key <= 57)) {
//                                            } else {
//                                                e.preventDefault();
//                                            }
//                                            var str = $(this).val();
//                                            if (str.length > 2 && str.length < 4) {
//                                                str = str.replace(/(\d{2})/, "$1/");
//                                            }
//                                            if (str.length > 4 && str.length < 6) {
//                                                str = str.replace(/(\d{2})/, "$1/");
//                                            }
//                                            if (str.length > 6 && str.length < 8) {
//                                                str = str.replace(/(\d{2})/, "$1/");
//                                            }
//                                            if (str.length > 8) {
//                                                str = str.substring(0, 8);
//                                            }
//                                            str = str.replace(/(\d{2})(\d{2})/, "$1/$2/");
//                                            str = str.replace(/(\d{2})(\d{2})(\d{4})/, "$1/$2/$3");
//                                            $(this).val(str);
//                                        });
                                        $(document).keypress(function (e) {
                                            var keycode = (e.keyCode ? e.keyCode : e.which);
                                            if (keycode == '13') {
                                                $("#submitBtn").focus();
                                            }
                                        });
                                        $(document).keydown(function (e) {
                                            var keycode = (e.keyCode ? e.keyCode : e.which);
                                            if (keycode == '33') {
                                                e.preventDefault();
                                                $("#docType").focus();
                                            }
                                        });
    </script>
</body>
</html>
