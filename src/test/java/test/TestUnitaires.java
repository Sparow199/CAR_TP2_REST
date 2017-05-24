package test; /**
 * Classe qui permet de faire des tests unitaires.
 */
import org.junit.Before;
import org.junit.Test;
import tp2.HtmlMakeTool;
import tp2.Ressources;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestUnitaires {

	private Ressources res;

	@Before
	public void init(){
		res = new Ressources();
	}

	/**
	 * Verifie que la ressource creee est non nulle
	 */
	@Test
	public void testRessource(){
		assertNotNull(res);
	}

	/**
	 * Verifie l'authentification avec les bons identifiants
	 * @throws IOException
	 */
	@Test
	public void testAuthentifiedWithGoodUsernamePassword() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		res.clientDisconnect();
	}

	/**
	 * Verifie que l'utilisateur n'est pas authentifie si les identifiants ne sont pas bon.
	 * @throws IOException
	 */
	@Test
	public void testAuthentifiedWithWrongPassword() throws IOException{
		res.connexion("test", "wrongpassword");
		assertFalse(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_FAILURE, res.connexion("totototo", "wrongpassword"));
	}

	/**
	 * Verifie que l'utilisateur n'est pas authentifie si les identifiants ne sont pas bon.
	 * @throws IOException
	 */
	@Test
	public void testAuthentifiedWithWrongUser() throws IOException{
		res.connexion("test", "rootroot");
		assertFalse(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_FAILURE, res.connexion("test", "rootroot"));

	}

	/**
	 * Verifie la deconnection du client
	 * @throws IOException
	 */
	@Test
	public void testClientDisconnect() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		assertEquals(HtmlMakeTool.QUIT, res.clientDisconnect());

	}


	/**
	 * Verifie le changement de dossier
	 * @throws IOException
	 */
	@Test
	public void testChangeWorkingDirectory() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		assertEquals(HtmlMakeTool.DIRECTORY_CHANGE, res.changeWorkDir("/"));
		assertEquals(HtmlMakeTool.QUIT, res.clientDisconnect());
	}

	/**
	 * Verifie la suppression d'un dossier qui existe pas
	 * @throws IOException
	 */
	@Test
	public void testRemoveWrongDirectory() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		assertEquals(HtmlMakeTool.DIRECTORY_REMOVE_ERROR, res.removeDir("azull"));
		assertEquals(HtmlMakeTool.QUIT, res.clientDisconnect());
	}

	/**
	 * Verifie la creation d'un dossier dans le repertoire /tmp
	 * @throws IOException
	 */
	@Test
	public void testMakeDirectory() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		assertEquals(HtmlMakeTool.DIRECTORY_CREATE, res.makeDirectory("tmp" , "folder"));
		assertEquals(HtmlMakeTool.DIRECTORY_REMOVE, res.removeDir("folder"));
		assertEquals(HtmlMakeTool.QUIT, res.clientDisconnect());
	}


	/**
	 * Verifie la suppression d'un dossier dans le repertoire /tmp
	 * @throws IOException
	 */
	@Test
	public void testDeleteDirectory() throws IOException{
		res.connexion("totototo", "rootroot");
		assertTrue(res.isAuthentified());
		assertEquals(HtmlMakeTool.CONNECTION_OK, res.connexion("totototo", "rootroot"));
		assertEquals(HtmlMakeTool.DIRECTORY_CREATE, res.makeDirectory("tmp" , "folderdelete"));
		assertEquals(HtmlMakeTool.DIRECTORY_REMOVE, res.removeDir("folderdelete"));
		assertEquals(HtmlMakeTool.QUIT, res.clientDisconnect());
	}








}
