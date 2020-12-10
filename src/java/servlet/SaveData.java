package servlet;

import dao.SuspectDao;
import initialize.ContextListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AddressDetails;
import model.FormDetails;
import model.OrderNoticeSpRef;
import model.RelativeDetails;
import org.apache.commons.io.FileUtils;

@WebServlet(name = "SaveData", urlPatterns = {"/SaveData"})
public class SaveData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null && session.getAttribute("userlogged").equals("1")) {
            String folder = request.getParameter("folder");
            folder = folder.substring(0, folder.length() - 1);
            String[] arr = folder.split("/");
            String ft_no = arr[arr.length - 3];
            String district = arr[arr.length - 4];
            FormDetails formDetails = new FormDetails();
            formDetails.setSuspectName(request.getParameter("suspectName"));
            formDetails.setFatherName(request.getParameter("fatherName"));
            formDetails.setMotherName(request.getParameter("motherName"));
            formDetails.setSpouseName(request.getParameter("spouseName"));
            formDetails.setGender(request.getParameter("gender"));
            formDetails.setMaritalStatus(request.getParameter("maritalStatus"));
            formDetails.setAge(request.getParameter("age"));
            formDetails.setDob(request.getParameter("dob"));
            formDetails.setMobileNo(request.getParameter("mobile"));
            formDetails.setPob(request.getParameter("placeOfBirth"));
            formDetails.setProfession(request.getParameter("occupation"));
            formDetails.setHeight(request.getParameter("height"));
            formDetails.setHairColor(request.getParameter("hair"));
            formDetails.setEyeColor(request.getParameter("eye"));
            formDetails.setComplexion(request.getParameter("complexion"));
            formDetails.setIdentificationMark(request.getParameter("identificationMark"));
            formDetails.setSpCaseNo(request.getParameter("spCaseNo"));
            formDetails.setImdtNo(request.getParameter("imdtNo"));
            formDetails.setFtCaseNo(request.getParameter("ftCaseNo"));
            ArrayList<RelativeDetails> relDetailsList = new ArrayList<>();
            String[] name = request.getParameterValues("memberName");
            String[] rel = request.getParameterValues("memberRel");
            String[] age = request.getParameterValues("memberAge");
            String[] dob = request.getParameterValues("memDob");
            String[] pob = request.getParameterValues("memPob");
            if (name != null) {
                for (int i = 0; i < name.length; i++) {
                    RelativeDetails relativeDetails = new RelativeDetails();
                    relativeDetails.setName(name[i]);
                    relativeDetails.setRelation(rel[i]);
                    relativeDetails.setMemberAge(age[i]);
                    relativeDetails.setPlaceOfBirth(pob[i]);
                    relativeDetails.setDateOfBirth(dob[i]);
                    relDetailsList.add(relativeDetails);
                }
            }
            AddressDetails addDetails = new AddressDetails();
            addDetails.setOriginVillage(request.getParameter("villageOrigin"));
            addDetails.setOriginPs(request.getParameter("psOrigin"));
            addDetails.setOriginDistrict(request.getParameter("districtOrigin"));
            addDetails.setOriginState(request.getParameter("stateOrigin"));
            addDetails.setOriginCountry(request.getParameter("countryOrigin"));
            addDetails.setVill(request.getParameter("village"));
            addDetails.setPs(request.getParameter("postOffice"));
            addDetails.setRevVill(request.getParameter("revVillage"));
            addDetails.setPoliceStation(request.getParameter("policeStation"));
            addDetails.setDistrict(request.getParameter("district"));
            addDetails.setState(request.getParameter("state"));
            addDetails.setPinCode(request.getParameter("pin"));
            addDetails.setLatitute(request.getParameter("lat"));
            addDetails.setLongitude(request.getParameter("lon"));
            addDetails.setVillageHead(request.getParameter("villHead"));
            addDetails.setVillHeadPh(request.getParameter("villHeadPh"));
            OrderNoticeSpRef orderNoticeSpRef = new OrderNoticeSpRef();
            orderNoticeSpRef.setFirstOrderDate(request.getParameter("firstOrderDate"));
            orderNoticeSpRef.setLastOrderDate(request.getParameter("lastOrderDate"));
            orderNoticeSpRef.setFtCaseNo(request.getParameter("ftCaseNo"));
            orderNoticeSpRef.setNoticeIssueDate(request.getParameter("noticeIssueDate"));
            orderNoticeSpRef.setHearingDate(request.getParameter("hearingDate"));
            orderNoticeSpRef.setNoticeThana(request.getParameter("noticeThana"));
            orderNoticeSpRef.setSpRefNo(request.getParameter("spCaseNo"));
            orderNoticeSpRef.setSpVillageName(request.getParameter("spVillageName"));
            orderNoticeSpRef.setSpPsName(request.getParameter("spPsName"));
            orderNoticeSpRef.setSpDistName(request.getParameter("spDistName"));
            orderNoticeSpRef.setFinalOrderDate(request.getParameter("finalOrderDate"));
            orderNoticeSpRef.setOpinionType(request.getParameter("opinionType"));
            orderNoticeSpRef.setNoticeType(request.getParameter("noticeType"));
            SuspectDao sd = new SuspectDao();
            String user = session.getAttribute("username").toString();
            if (sd.doSaveData(formDetails, relDetailsList, addDetails, orderNoticeSpRef, user, ft_no, district, folder)) {
                // Moving the file after saving
                String imagePathRead = getServletContext().getInitParameter("imgPathRead");
                String imgPathWrite = getServletContext().getInitParameter("imgPathWrite");
                String writePath = folder.replace(imagePathRead, imgPathWrite);
                FileUtils.moveDirectory(new File(folder), new File(writePath));
                System.out.println("Folder Moved Successfully");
                Connection conn = null;
                try {
                    conn = ContextListener.ds.getConnection();
                } catch (SQLException e) {
                    System.out.println("Connection Exception : Image Dao - " + e.getMessage());
                }
                PreparedStatement ps = null;
                try {
                    int index = 1;
                    ps = conn.prepareStatement("UPDATE imagemaster SET FLAG2 = \"MOVED\" WHERE folder=?");
                    ps.setString(index++, folder + "/");
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Exception saving file moving details: " + e.getMessage());
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("Exception PS Closing : Save Data - " + e.getMessage());
                    }
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("Exception Conn Closing : Save Data - " + e.getMessage());
                    }
                }
                request.setAttribute("msg", "<div class=\"alert alert-success\">Data Saved Successfully</div>");
            } else {
                request.setAttribute("msg", "<div class=\"alert alert-danger\">Data Saving Failed</div>");
            }
            String imagePathRead = getServletContext().getInitParameter("imgPathRead");
            request.setAttribute("imagePathRead", imagePathRead);
            request.getRequestDispatcher("./readFile.jsp").forward((ServletRequest) request, (ServletResponse) response);
        } else {
            request.setAttribute("msg", "<div class=\"alert alert-danger\">Unauthorised ! Please try to log in.</div>");
            request.getRequestDispatcher("./login.jsp").forward((ServletRequest) request, (ServletResponse) response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
