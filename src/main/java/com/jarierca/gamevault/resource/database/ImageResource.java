package com.jarierca.gamevault.resource.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import com.jarierca.gamevault.config.UploadConfig;
import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.entity.database.Developer;
import com.jarierca.gamevault.entity.database.Genre;
import com.jarierca.gamevault.entity.database.Platform;
import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.service.database.ImageService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResource {

	@Inject
	ImageService imageService;

	@Inject
	UploadConfig uploadConfig;

	@GET
	@Path("/p/{path:.+}")
	public Response getImage(@PathParam("path") String path) {
		File baseDir = new File(System.getProperty("user.dir"), "images");

		try {
			File requestedFile = new File(baseDir, path).getCanonicalFile();

			if (!requestedFile.getPath().startsWith(baseDir.getCanonicalPath())) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Access to the requested resource is forbidden.").build();
			}

			if (!requestedFile.exists() || !requestedFile.isFile()) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			String mimeType = Files.probeContentType(requestedFile.toPath());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			return Response.ok(requestedFile, mimeType).build();
		} catch (IOException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An error occurred while processing the file.").build();
		}
	}

	@GET
	@Path("/videogames/{videogameId}/image/{imageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO getImageByVideogameId(@PathParam("videogameId") Long videogameId,
			@PathParam("imageId") Long imageId) {
		return imageService.findImageByVideogameIdAndImageId(videogameId, imageId);
	}

	@GET
	@Path("/videogames/{id}")
	@Produces("image/png")
	public List<ImageDTO> getImagesByVideogameId(@PathParam("id") Long videogameId) {
		return imageService.findImagesByField(Videogame.class.getSimpleName().toLowerCase(), videogameId);
	}

	@GET
	@Path("/platforms/{id}")
	@Produces("image/png")
	public List<ImageDTO> getImagesByPlatformId(@PathParam("id") Long platformId) {
		return imageService.findImagesByField(Platform.class.getSimpleName().toLowerCase(), platformId);
	}

	@GET
	@Path("/developers/{id}")
	@Produces("image/png")
	public List<ImageDTO> getImagesByDeveloperId(@PathParam("id") Long developerId) {
		return imageService.findImagesByField(Developer.class.getSimpleName().toLowerCase(), developerId);
	}

	@GET
	@Path("/publishers/{id}")
	@Produces("image/png")
	public List<ImageDTO> getImagesByPublisherId(@PathParam("id") Long publisherId) {
		return imageService.findImagesByField(Publisher.class.getSimpleName().toLowerCase(), publisherId);
	}

	@GET
	@Path("/genres/{id}")
	@Produces("image/png")
	public List<ImageDTO> getImagesByGenreId(@PathParam("id") Long genreId) {
		return imageService.findImagesByField(Genre.class.getSimpleName().toLowerCase(), genreId);
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadImage(@Context HttpHeaders headers, @PathParam("file") InputStream fileInputStream,
			@PathParam("name") String fileName) {
		try {
			File uploadDir = new File("/path/to/your/project/images/videogames/");
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			File file = new File(uploadDir, fileName);
			try (FileOutputStream out = new FileOutputStream(file)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = fileInputStream.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
			}

			return Response.ok("Imagen subida correctamente").build();
		} catch (IOException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error al subir la imagen: " + e.getMessage()).build();
		}
	}

}
