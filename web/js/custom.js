/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function doSubmitForm() {
    $(".errorTxt").hide();
    if (!isDate($("#firstOrderDate").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Enter First Order Date in DD/MM/YYYY format", "firstOrderDate");
    } else if (!isDate($("#lastOrderDate").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Enter Last Order Date in DD/MM/YYYY format", "lastOrderDate");
    } else if ($("#ftCaseNo").val().trim() === "") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please enter FT Case No.", "ftCaseNo");
    } 
	/*else if (!isDate($("#noticeIssueDate").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Enter Notice issue date in DD/MM/YYYY format", "noticeIssueDate");
    } else if (!isDate($("#hearingDate").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Enter hearing date in DD/MM/YYYY format", "hearingDate");
    }*/
	else if ($("#noticeThana").val().trim() !== "" && checkSpecialCharacters($("#noticeThana").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Special Characters are not Allowed", "noticeThana");
    } 
/*	else if ($("#noticeType").val() === "---") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please select Notice Type", "noticeType");
    } */
	else if ($("#finalOrderDate").val().trim() !== "" && !isDate($("#finalOrderDate").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Enter Final Order date in DD/MM/YYYY format", "finalOrderDate");
    } else if ($("#finalOrderDate").val().trim() !== "" && $("#opinionType").val() === "---") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please select opinion Type", "opinionType");
    }
//    else if ($("#opinionType").val() === "---") {
//        return showMessage("Please select Notice Type", "opinionType");
//    }
    else if ($("#spCaseNo").val().trim() === "" && $("#imdtNo").val().trim() === "") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please Enter SP Ref. or IMDT No.", "spCaseNo");
    }
//    else if ($("#imdtNo").val().trim() === "") {
//        $(".entryCard").hide();
//        $("#orderCard").show();
//        return showMessage("Please Enter IMDT No.", "imdtNo");
//    }
//    else if ($("#spStateName").val().trim() === "") {
//        return showMessage("Enter SP Case No.", "spStateName");
//    }
    else if ($("#spDistName").val().trim() === "---") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please select District", "spDistName");
    } else if ($("#spPsName").val().trim() === "---") {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Please select Police Station", "spPsName");
    } else if ($("#spVillageName").val().trim() !== "" && checkSpecialCharacters($("#spVillageName").val().trim())) {
        $(".entryCard").hide();
        $("#orderCard").show();
        return showMessage("Special Characters are not Allowed", "spVillageName");
    } else if ($("#suspectName").val().trim() === "") {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter Suspect Name", "suspectName");
    } else if (checkSpecialCharacters($("#suspectName").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "suspectName");
    } else if ($("#fatherName").val().trim() !== "" && checkSpecialCharacters($("#fatherName").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "fatherName");
    } else if ($("#motherName").val().trim() !== "" && checkSpecialCharacters($("#motherName").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "motherName");
    } else if ($("#spouseName").val().trim() !== "" && checkSpecialCharacters($("#spouseName").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "spouseName");
    } else if ($("#age").val().trim() !== "" && isNaN($("#age").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter age in digits", "age");
    } else if ($("#age").val().trim() !== "" && ($("#age").val().trim() < 0 || $("#age").val().trim() > 120)) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter a valid age", "age");
    } else if ($("#dob").val().trim().trim() !== "" && !isDate($("#dob").val().trim())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter DOB in DD/MM/YYYY format", "dob");
    } else if ($("#gender").val() === "---") {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Please select gender", "gender");
    } else if ($("#mobile").val().trim() !== "" && isNaN($("#mobile").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter Mobile in digits", "mobile");
    } else if ($("#placeOfBirth").val().trim() !== "" && checkSpecialCharacters($("#placeOfBirth").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Enter Place of Birth", "placeOfBirth");
    } else if ($("#occupation").val().trim() !== "" && checkSpecialCharacters($("#occupation").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "occupation");
    }
//    else if ($("#height").val().trim() !== "" && checkSpecialCharacters($("#height").val())) {
//        return showMessage("Special Characters are not Allowed", "height");
//    }
    else if ($("#hair").val().trim() !== "" && checkSpecialCharacters($("#hair").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "hair");
    } else if ($("#eye").val().trim() !== "" && checkSpecialCharacters($("#eye").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "eye");
    } else if ($("#complexion").val().trim() !== "" && checkSpecialCharacters($("#complexion").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "complexion");
    } else if ($("#identificationMark").val().trim() !== "" && checkSpecialCharacters($("#identificationMark").val())) {
        $(".entryCard").hide();
        $("#formCard").show();
        return showMessage("Special Characters are not Allowed", "identificationMark");
    } else if ($("#villageOrigin").val().trim() !== "" && checkSpecialCharacters($("#villageOrigin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "villageOrigin");
    } else if ($("#psOrigin").val().trim() !== "" && checkSpecialCharacters($("#psOrigin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "psOrigin");
    } else if ($("#districtOrigin").val().trim() !== "" && checkSpecialCharacters($("#districtOrigin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "districtOrigin");
    } else if ($("#stateOrigin").val().trim() !== "" && checkSpecialCharacters($("#stateOrigin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "stateOrigin");
    } else if ($("#countryOrigin").val().trim() !== "" && checkSpecialCharacters($("#countryOrigin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "countryOrigin");
    } else if ($("#village").val().trim() !== "" && checkSpecialCharacters($("#village").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "village");
    } else if ($("#postOffice").val().trim() !== "" && checkSpecialCharacters($("#postOffice").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "postOffice");
    } else if ($("#revVillage").val().trim() !== "" && checkSpecialCharacters($("#revVillage").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "revVillage");
    } else if ($("#policeStation").val().trim() !== "" && checkSpecialCharacters($("#policeStation").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "policeStation");
    } else if ($("#district").val().trim() !== "" && checkSpecialCharacters($("#district").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "district");
    } else if ($("#state").val().trim() !== "" && checkSpecialCharacters($("#state").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "state");
    } else if ($("#pin").val().trim() !== "" && isNaN($("#pin").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Invalid Pin Code", "pin");
    }
//    
//     else if ($("#lat").val().trim() !== "" && checkSpecialCharacters($("#lat").val())) {
//        return showMessage("Special Characters are not Allowed", "lat");
//    }
//    
//     else if ($("#lon").val().trim() !== "" && checkSpecialCharacters($("#lon").val())) {
//        return showMessage("Special Characters are not Allowed", "lon");
//    }
//    
    else if ($("#villHead").val().trim() !== "" && checkSpecialCharacters($("#villHead").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Special Characters are not Allowed", "villHead");
    } else if ($("#villHeadPh").val().trim() !== "" && isNaN($("#villHeadPh").val())) {
        $(".entryCard").hide();
        $("#addCard").show();
        return showMessage("Please enter a valid phone number", "villHeadPh");
    }

    for (var i = 1; i <= memberCount; i++) {
        if ($("#memberName_" + i).val().trim() === "") {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Please Enter Member Name", "memberName_" + i + "_");
        } else if (checkSpecialCharacters($("#memberName_" + i).val())) {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Special Characters are not Allowed", "memberName_" + i + "_");
        } else if ($("#memberRel_" + i).val() === "---") {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Please Select Relation", "memberRel_" + i + "_");
        } else if ($("#memberAge_" + i).val().trim() !== "" && isNaN($("#memberAge_" + i).val().trim())) {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Enter a Valid Age", "memberAge_" + i + "_");
        } else if ($("#memberAge_" + i).val().trim() !== "" && ($("#memberAge_" + i).val().trim() < 0 || $("#memberAge_" + i).val().trim() > 120)) {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Enter a Valid Age", "memberAge_" + i + "_");
        } else if ($("#memDob_" + i).val().trim().trim() !== "" && !isDate($("#memDob_" + i).val().trim())) {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Enter DOB in DD/MM/YYYY format", "memDob_" + i + "_");
        } else if ($("#memPob_" + i).val().trim().trim() !== "" && checkSpecialCharacters($("#memPob_" + i).val())) {
            $(".entryCard").hide();
            $("#relCard").show();
            return showMessage("Special Characters are not Allowed", "memPob_" + i + "_");
        }
    }

    if (confirm("Are you sure you want to submit the form ?")) {
        $("#suspectForm").submit();
    }
    return true;
}

function checkSpecialCharacters(str) {
    var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,<>\/?]+/;
    if (format.test(str)) {
        return true;
    } else {
        return false;
    }
}

function showMessage(msg, id) {
    alert(msg);
    $("#" + id + "Error").html(msg);
    $("#" + id + "Error").show();
    return false;
}

function isDate(str) {
    var parms = str.split(/[/]/);
    var yyyy = parseInt(parms[2], 10);
    var mm = parseInt(parms[1], 10);
    var dd = parseInt(parms[0], 10);
    var date = new Date(yyyy, mm - 1, dd, 0, 0, 0, 0);
    return mm === (date.getMonth() + 1) && dd === date.getDate() && yyyy === date.getFullYear();
}
