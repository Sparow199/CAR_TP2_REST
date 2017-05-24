package tp2;
/**
 * HtmlMakeTool est une classe qui permet de construire les pages html
 */
public class HtmlMakeTool {

    /** L'url de la passerelle REST */
    final static private String url = "http://localhost:8080/rest/tp2/api";


    public static final String HEADER =
            "<head>" +
                    "<meta charset=\"utf-8\"/>" +
                    "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>" +
                    "<script src=\"//cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js\"></script>" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.8/js/fileinput.min.js\"></script>" +
                    "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css\"/>" +
                    "<script src=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/alertify.min.js\"></script>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.8/css/fileinput.css\"/>"+
                    "</head>";

    public static final String HEADER_REDIRECT_LIST =
            "<head>" +
                    "<meta charset=\"utf-8\"/ http-equiv=\"refresh\" content=\"1; URL="+url+"/list/\">" +
                    "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>" +
                    "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\"/>"+
                    "<script src=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/alertify.min.js\"></script>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.8/css/fileinput.css\"/>"+
                    "</head>";

    public static final String HEADER_REDIRECT_LOGIN =
            "<head>" +
                    "<meta charset=\"utf-8\"/ http-equiv=\"refresh\" content=\"1; URL="+url+"\">" +
                    "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>" +
                    "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\"/>"+
                    "<script src=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/alertify.min.js\"></script>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/semantic.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/bootstrap.rtl.min.css\"/>"+
                    "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.8/css/fileinput.css\"/>"+
                    "</head>";

    public static final String FORM_CONNECT =
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER +
                    "<body>" +
                    "<div class=\"row text-center\">" +
                    "<div class=\"col-md-offset-4 col-md-4\">" +
                    "<h1>FTP connect</h1>" +
                    "<br>" +
                    "</div>" +
                    "</div>" +

                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-4 col-md-4\">" +
                    "<form method='POST' action='" + url + "/connexion' enctype='multipart/form-data'>" +
                    "<div class=\"input-group\">" +
                    "<span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-user\"></i></span>" +
                    "<input type=\"text\" id=\"login\" name=\"username\" class=\"form-control\" placeholder=\"Login\"/>" +
                    "</div>" +
                    "<br>" +

                    "<div class=\"input-group\">" +
                    "<span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span>" +
                    "<input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\"/>" +
                    "</div>" +
                    "<br>" +

                    "<div class=\"form-group col-md-offset-4 col-md-4\">" +
                    "<input class=\"btn btn btn-success btn-block\" name=\"submit\" type=\"submit\" value=\"Connect\"/>" +
                    "</div>" +
                    "</form>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String CONNECTION_OK =
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST +
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +
                    "<script>" +
                    "alertify.success('Connected')" +
                    "</script>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String CONNECTION_FAILURE =
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LOGIN +
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Error ! Login/Password not correct')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";



    public static String QUIT =
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LOGIN +
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Client disconnected')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String CDUP=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Parent Directory')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
    public static String CDUP_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST +
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to reach parent directory')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";



    public static String FILE_DELETE=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('File deleted')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";


    public static String FILE_DELETE_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to remove file')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";


    public static String DIRECTORY_CREATE=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Directory created')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_CREATE_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Directory already exists')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_CHANGE=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Directory changed')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_CHANGE_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to change directory')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_REMOVE=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Directory removed')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";


    public static String DIRECTORY_REMOVE_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to remove directory')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_RENAME=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('Directory renamed')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String DIRECTORY_RENAME_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +
                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to rename directory')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";




    public static String FILE_UPLOAD=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +

                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.success('File uploaded')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

    public static String FILE_UPLOAD_ERROR=
            "<!DOCTYPE html>" +
                    "<html>" +
                    HEADER_REDIRECT_LIST+
                    "<body>" +

                    "<div class=\"row\">" +
                    "<div class=\"col-md-offset-2 col-md-8\">" +

                    "<script>" +
                    "alertify.error('Impossible to upload file')" +
                    "</script>" +

                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";





    /**
     * Create a button to download file
     * @param dir the directory where is the file
     * @param file the file to download
     * @return A html String with the representation of this button
     */
    public static String addGetButton(String dir, String file) {
        return "<form method=\"GET\"  " +
                "action="+
                "\""+url+"/get/" + dir + file + "\">" +
                "<button onclick=\"javascript:window.location.reload()\" class=\"btn btn-info btn-xs glyphicon glyphicon-download\" type=\"submit\" value=\"Get\" /></button>" +
                "</form>";
    }

    /**
     * Create a button to delete file
     * @param dir the directory where is the file
     * @param file the file to delete
     * @return A html String with the representation of this button
     */
    public static String addDeleteButton(String dir, String file) {
        return "<form method=\"GET\"" +
                "action=" +
                "\""+url+"/delete/" + dir + file + "\">" +
                "<button  onclick=\"javascript:window.location.reload()\" class=\"btn btn-danger btn-xs glyphicon glyphicon-trash\" type=\"submit\" value=\"Get\" /></button>" +
                "</form>";
    }
}
