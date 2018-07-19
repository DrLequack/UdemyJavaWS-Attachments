package com.spring.restws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

@Path("/fileService")
@Service
public class FileService 
{
	private static final String FILE_PATH = "D:/downloads/test/";
	private static final String FILE_NAME = "uploadedFile.jpg";
	
	@Path("/upload")
	@POST
	public void upload (List<Attachment> attchments)throws FileNotFoundException, IOException
	{
		for (Attachment attachment : attchments) 
		{
			copyFile(attachment.getDataHandler().getInputStream());
		}
	}
	
	private void copyFile(InputStream input) throws FileNotFoundException, IOException
	{
		OutputStream out = null;
		int read = 0;
		byte bytes[] = new byte[4096];
		
		out = new FileOutputStream(new File(FILE_PATH + FILE_NAME));
		
		while((read = input.read(bytes)) != -1)
		{
			out.write(bytes, 0, read);
		}
		
		out.flush();
		out.close();
	}
}
