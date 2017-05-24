package tp2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe qui permet de gerer les ressources
 */


@Path("/api")
public class Ressources {

	/** L'url de la passerelle REST */
	final static private String url = "http://localhost:8080/rest/tp2/api";

	/** Le serveur FTP */
	private FTPClient client;

	/** L'hôte */
	private String host;

	/** Le port */
	private int port;

	/** Le booleen permettant de savoir si l'utilisateur est authetifie */
	private boolean authentified = false;

	/**
	 * Constructeur de Ressources.
	 */
	public Ressources() {
		this.host = Constants.host;
		this.port = Constants.port;

		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		conf.setServerLanguageCode(FTPClientConfig.SYST_UNIX);

		client = new FTPClient();
		client.configure(conf);
	}

	/**
	 * Permet de savoir si l'utilisateur est authentifie.
	 * @return true si l'utilisateur est authentifie, false sinon.
	 */
	public boolean isAuthentified() {
		return authentified;
	}

	/**
	 * Retourne la page html contenant un formulaire pour s'authentifier.
	 * @return la page html contenant un formulaire pour s'authentifier.
	 */
	@GET
	@Produces("text/html; charset=UTF-8")
	@Path("")
	public String login() {

		return HtmlMakeTool.FORM_CONNECT;

	}

	/**
	 * Permet la connexion au serveur et l'authentification.
	 * @param username Nom d'utilisateur
	 * @param password Mot de passe
	 * @return une page html indiquant si on est bien authentifie/connecte
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/html; charset=UTF-8")
	@Path("/connexion")
	public String connexion(@Multipart("username") String username, @Multipart("password") String password)
			throws IOException {

		try {
			client.connect(host, port);
			this.authentified = client.login(username, password);

			if (isAuthentified()) {
				return HtmlMakeTool.CONNECTION_OK;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		client.disconnect();
		return HtmlMakeTool.CONNECTION_FAILURE;
	}

	/**
	 * Permet de telecharger le fichier situe sur le serveur.
	 * @param path Le chemin du fichier à recuperer
	 * @return le fichier choisi
	 */
	@GET
	@Produces("application/octet-stream")
	@Path("/get{path : (/path)?}")
	public File get(@PathParam("path") String path) {
		File file = null;

		if (isAuthentified()) {
			try {
				String[] paths = path.split("/");
				String filename = paths[paths.length - 1];

				file = new File(filename);
				FileOutputStream output = new FileOutputStream(file);

				client.retrieveFile(path, output);
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return file;
	}

	/**
	 * Affiche la liste des fichiers contenu dans le folderpath seulement si l'utilisateur est authentifie
	 * @param folderPath chemin vers le dossier souhaite
	 * @return la page html contenant la liste des fichiers
	 */
	@GET
	@Produces("text/html")
	@Path("/list{folder_path : (/folder_path)?}")
	public String list(@PathParam("folder_path") String folderPath) {
		FTPFile[] ftpFiles = null;

		StringBuilder html = new StringBuilder();

		if (isAuthentified()) {
			try {
				if (folderPath != null) {
					ftpFiles = client.listFiles(folderPath);
					folderPath += "";

					html.append("<!DOCTYPE html><html>" + HtmlMakeTool.HEADER + "<body style=\"padding-top: 120px;\">");
					html.append("<nav class=\"navbar navbar-default navbar-fixed-top\">" + "<p class=\"navbar-brand\">").append(folderPath).append("</p>").append("<div class=\"container\">").append("<ul class=\"nav navbar-nav navbar-right\">").append("<li><a href=\"").append(url).append("/cdup/\">HOME</a></li>");


					html.append("<li><a href=\"" + url + "/disconnect/\">DISCONNECT</a></li>" + "</ul>" + "</div>" + "<input class=\"form-control\" id=\"dir\" type=\"hidden\" value=\"").append(folderPath).append("\" />");






					html.append("<form method='POST' action='" + url + "/upload' enctype='multipart/form-data'>\n");

					html.append("<div class=\"input-group\">\n" + "<span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-open\"></i></span>" + "<input style=\"hidden\"type=\"file\" name=\"file\" class=\"form-control\" id=\"files\">\n" + "</div>");

					html.append("<input type=\"hidden\" name=\"path\" value=\"").append(folderPath).append("\">");

					html.append("<div class=\"input-group\">\n" + "<span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-info-sign\"></i></span>" + "<input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"File name with extension\">\n" + "<br>" + "</div>");

					html.append("</nav>" + "<br>");

					html.append("<div class=\"input-group input-center\">\n" + "<button type=\"submit\" value=\"Envoyer\" class=\"btn btn-success \" >" + "<span class=\"glyphicon glyphicon-upload\"> Upload</span>" + "</button>" + "<br>" + "</div>" + "</form> ");



					html.append("<div class=\"col-md-offset-2 col-md-8\">" + "<div class=\"col-md-8\"></div>" + "<table id=\"files\" class=\"table\">" + "<thead><tr><th style=\"text-align:center\">Nom</th><th class=\"size\" style=\"text-align:center\">Taille</th><th class=\"date\" style=\"text-align:center\">Dernière modification</th><th style=\"text-align:center\">Action(s)</th></tr></thead>" + "<tbody>");


				} else {
					ftpFiles = client.listFiles("");
					folderPath = "";
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}


			for (FTPFile file: ftpFiles) {
				String cmd;
				if (file.isDirectory()) {
					cmd = "/list/";

					html.append("<tr style=\"height: 43px;\">" + "<td><span class=\"glyphicon glyphicon-folder-open\" style=\"width: 40px\" aria-hidden=\"true\"></span>" + "<a href=\"" + url).append(cmd).append(folderPath).append(file.getName()).append("/\">").append(file.getName()).append("</a></td>").append("<td></td>").append("<td style=\"text-align:center\">").append(Converter.getConvertedDate(file.getTimestamp().getTime())).append("</td>").append("<td></td>").append("</tr>");

				} else {

					html.append("<tr style=\"height: 43px;\">" + "<td><span class=\"glyphicon glyphicon-file\" style=\"width: 40px\" aria-hidden=\"true\"></span>").append(file.getName()).append("</td>").append("<td style=\"text-align:right\">").append(Converter.getConvertedSize(file.getSize())).append("</td>").append("<td style=\"text-align:center\">").append(Converter.getConvertedDate(file.getTimestamp().getTime())).append("</td>");

				}

				if (!file.isDirectory())
					html.append("<td class=\"col-md-2\" style=\"text-align:center\"><ul class=\"list-inline\"><li>").append(HtmlMakeTool.addGetButton(folderPath, file.getName())).append("<li/>").append("<li>").append(HtmlMakeTool.addDeleteButton(folderPath, file.getName())).append("</li><ul></td>");
			}

			html.append("</tr>");
		}
		return html.toString();
	}

	/**
	 * Permet d'uploader un fichier vers le serveur, seulement si on est authentifie.
	 * @param fichier le fichier a uploade
	 * @param name Le nouveau nom du fichier
	 * @param path le chemin du fichier a uploade
	 * @return Une page html contenant un message qui indique le success
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/html; charset=UTF-8")
	@Path("/upload")
	public String uploadFile(@Multipart("file") InputStream fichier, @Multipart("name") String name,
						 @Multipart("path") String path) throws IOException {


		if (isAuthentified()) {
			Boolean stat = client.storeFile(path + name, fichier);

			if (!stat){

				return HtmlMakeTool.FILE_UPLOAD_ERROR;

			}


		}

		return HtmlMakeTool.FILE_UPLOAD;
	}



	/**
	 * Permet cree un dossier seulement si on est authentifie.
	 * @param name Le nom du repertoire
	 * @param dir le chemin du nouveau dossier
	 * @return Une page html indiquant que la creation avec success
	 * @throws IOException
	 */

	@GET
	@Path("/mkdir/{folder_path: .*}/{filepath}")
	@Produces("text/html; charset=UTF-8")
	public String makeDirectory(@PathParam("folder_path") String dir,
								@PathParam("filepath") String name) throws IOException {
		try {
		if (isAuthentified()) {

            client.cwd(dir);

			File liste[] = new File("/" + dir).listFiles();

			for (File fichier : liste) {
				if (fichier.getName().equals(name)) {
					if (fichier.isDirectory()) {
						return HtmlMakeTool.DIRECTORY_CREATE_ERROR;
					}
				}

				Boolean stat = client.makeDirectory(name);

				if (!stat){

					return HtmlMakeTool.DIRECTORY_CREATE_ERROR;

				}


			}
		}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return HtmlMakeTool.DIRECTORY_CREATE;
	}


	/**
	 * Permet de supprimer un fichier, seulement si l'utilisateur est authentifie
	 * @param filepath le chemin du fichier à supprimer
	 * @return une page html indiquant que le fichier a bien ete supprime
	 */
	@GET
	@Produces("text/html; charset=UTF-8")
	@Path("/delete{filepath : (/filepath)?}")
	public String deleteFile(@PathParam("filepath") String filepath) {

		if (isAuthentified()) {
			try {
				Boolean stat = client.deleteFile(filepath);

				if (!stat){

					return HtmlMakeTool.FILE_DELETE_ERROR;

				}


			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return HtmlMakeTool.FILE_DELETE;
	}



	/**
	 * cette methode Permet de renommer un fichier ou un repertoire sur le serveur FTP
	 * @param folder_path Le chemin vers le repertoire
	 * @param oldfilename L'ancien nom du fichier ou du repertoire a renommer
     * @param newfilename Le nouveau nom du fichier ou du repertoire a renommer
	 * @return Une confirmation du renommage et un lien pour retourner a la liste des fichiers
	 * @throws IOException
	 */
	@GET
	@Path("/rename/{folder_path: .*}/{oldfilepath}/{newfilepath}")
	@Produces("text/html; charset=UTF-8")
	public String rename( @PathParam("folder_path") String folder_path,
                          @PathParam("oldfilepath") String oldfilename,
                          @PathParam("newfilepath") String newfilename) throws IOException {


		if (isAuthentified()) {
			try {

                client.cwd(folder_path);
				Boolean stat = client.rename(oldfilename, newfilename);

				if (!stat){

					return HtmlMakeTool.DIRECTORY_RENAME_ERROR;

				}


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return HtmlMakeTool.DIRECTORY_RENAME;
	}



	/**
	 * cette methode Permet de supprimer un repertoire sur le serveur FTP
	 * @param folder_path Le chemin du fichier ou du repertoire a renommer
	 * @return une page html indiquant une confirmation de la suppression
	 * @throws IOException
	 */
	@GET
	@Path("/rmdir{folder_path: .*}")
	@Produces("text/html; charset=UTF-8")
	public String removeDir(@PathParam("folder_path") String folder_path) throws IOException {


		if (isAuthentified()) {
			try {

				Boolean stat = client.removeDirectory(folder_path);

				if (!stat){

					return HtmlMakeTool.DIRECTORY_REMOVE_ERROR;

				}


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return HtmlMakeTool.DIRECTORY_REMOVE;
	}


    /**
     * cette methode Permet de changer repertoire sur le serveur FTP
     * @param folder_path Le chemin du fichier ou du repertoire a renommer
     * @return une page html indiquant une confirmation de la suppression
     * @throws IOException
     */
    @GET
    @Path("/cwd/{folder_path: .*}")
    @Produces("text/html; charset=UTF-8")
    public String changeWorkDir(@PathParam("folder_path") String folder_path) throws IOException {


        if (isAuthentified()) {
            try {

                boolean stat = client.changeWorkingDirectory(folder_path);
				if (!stat){

					return HtmlMakeTool.DIRECTORY_CHANGE_ERROR;

				}



			} catch (IOException e) {
                e.printStackTrace();
            }

        }
        return HtmlMakeTool.DIRECTORY_CHANGE;
    }


	/**
	 * cette methode Permet d'aller au repertoire racine sur le serveur FTP
	 * @return une page html indiquant le retour a la racine
	 * @throws IOException
	 */
	@GET
	@Path("/cdup")
	@Produces("text/html; charset=UTF-8")
	public String changeToParentDir() throws IOException {


		if (isAuthentified()) {
			try {

				boolean stat = client.changeToParentDirectory();
				if (!stat){

					return HtmlMakeTool.CDUP_ERROR;

				}



			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return HtmlMakeTool.CDUP;
	}



	/**
	 * Permet la deconnexion de l'utilisateur.
     * @return une page html indiquant ne confirmation de deconnection.
     * @throws IOException
	 */
	@GET
	@Path("/disconnect")
	@Produces("text/html; charset=UTF-8")
	public String clientDisconnect() {

		if (isAuthentified()) {
			try {

				client.disconnect();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return HtmlMakeTool.QUIT;

	}




}