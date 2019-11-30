package com.saviasoft.generacion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlCon {
	public static void main(String[] args) {
//		cambioCharacterAVarChar();
		cambiosLasReferencias();
	}

	public static void cambioCharacterAVarChar() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://10.160.5.78:5432/faeDesarrollo",
				"administrador", "@DMIND3$@RR0LL0F@3")) {

			System.out.println("Java JDBC PostgreSQL Example");
			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			String tablasString = "adjunto,adjuntocarga,adjuntocargafinanciero,archivoblob,articulofalta,ascensopersonal,audidbfae01,banco,bonificacion,calificacionperson,calificadores,canton,capitulofalta,cargo,cargodesemp,ciudad,comision,condecoracion,condecoracionperso,confidencial_1,contadorportal,conyuge,ctapersonal,curso,cursopersonal,datospersonales,descargaarchivo,detallepuntajeppff,direccion,docuaprobacion,especialidad,estadocivil,estructuraorganica,familiar,fichamedica,formacioncivil,formulario107,funcional,grado,grupo,hegresosrol,hijo,hingresosrol,hrolpago,hrolpagopersonal,ingresosrol,instituto,literalfalta,menasjecorreomasivo,nivelinstruccion,niveltabla,niveltabla1,novedad,oidkeeper,pais,parametropersonal,parroquia,pase,periodofiscal,personal,profesorado,provincia,pruebappff,puntaje,puntaje1,puntajeppff,region,regulacionrespon,relacionconyugal,relacionparentezco,reparto,rereserva,responsabledescarga,responsableretenci,restriccionap7,restriccionap7_usuarios_abilitados,retencionjudicial,rubrorol,sancion,sancionaplicada,solicambiodetalle,solicitudcambio,tabla,tabladetablas,tablappff,tablapruebappff,tipo,tipo1,tipoprueba,tiposangre,tipotabla,titulo,unidadusuario,vigencia";
			String[] tablas = tablasString.split(",");

			for (String tableName : tablas) {
				String stringFinal = "";
				String sql = "SELECT DISTINCT table_name, column_name," + "case"
						+ "    when domain_name is not null then domain_name"
						+ "    when data_type='character' THEN 'character('||character_maximum_length||')'"
						+ "    when data_type='numeric' THEN 'numeric('||numeric_precision||','||numeric_scale||')'"
						+ "    else data_type" + " end as myType" + " FROM information_schema.columns"
						+ " WHERE table_schema='dbfae01'" + " AND table_name = '" + tableName + "';";
//				System.out.println("Sql: " + sql);
				ResultSet rs = statement.executeQuery(sql);

//				ALTER TABLE dbfae01.condecoracion
//			    ALTER COLUMN recibebonificacion TYPE character varying (1) COLLATE pg_catalog."default";
				String alter = "ALTER TABLE dbfae01.";
				String alterColumn = " ALTER COLUMN ";
				String type = " TYPE character varying";
				String descripcion = " COLLATE pg_catalog.\"default\";";
				while (rs.next()) {
					String nombreColumna = rs.getString(2);
					String tipoColumna = rs.getString(3);
					if (tipoColumna.contains("character") && !tipoColumna.contains("varying")) {
//						System.out.println("sii: "+tipoColumna);
						String maxvalue = tipoColumna.substring(tipoColumna.indexOf("(") + 1, tipoColumna.indexOf(")"));
//						System.out.println(maxvalue);
						stringFinal = alter + tableName + alterColumn + nombreColumna + type + "(" + maxvalue + ")"
								+ descripcion;
						System.out.println(stringFinal);
					}
//					stringFinal = stringFinal + "," + rs.getString(2);

				}
			}

		} /*
			 * catch (ClassNotFoundException e) {
			 * System.out.println("PostgreSQL JDBC driver not found."); e.printStackTrace();
			 * }
			 */ catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public static void cambiosLasReferencias() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://10.160.5.78:5432/faeDesarrollo",
				"administrador", "@DMIND3$@RR0LL0F@3")) {

			System.out.println("Java JDBC PostgreSQL Example");
			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			String tablasString = "adjunto,adjuntocarga,adjuntocargafinanciero,archivoblob,articulofalta,ascensopersonal,audidbfae01,banco,bonificacion,calificacionperson,calificadores,canton,capitulofalta,cargo,cargodesemp,ciudad,comision,condecoracion,condecoracionperso,confidencial_1,contadorportal,conyuge,ctapersonal,curso,cursopersonal,datospersonales,descargaarchivo,detallepuntajeppff,direccion,docuaprobacion,especialidad,estadocivil,estructuraorganica,familiar,fichamedica,formacioncivil,formulario107,funcional,grado,grupo,hegresosrol,hijo,hingresosrol,hrolpago,hrolpagopersonal,ingresosrol,instituto,literalfalta,menasjecorreomasivo,nivelinstruccion,niveltabla,niveltabla1,novedad,oidkeeper,pais,parametropersonal,parroquia,pase,periodofiscal,personal,profesorado,provincia,pruebappff,puntaje,puntaje1,puntajeppff,region,regulacionrespon,relacionconyugal,relacionparentezco,reparto,rereserva,responsabledescarga,responsableretenci,restriccionap7,restriccionap7_usuarios_abilitados,retencionjudicial,rubrorol,sancion,sancionaplicada,solicambiodetalle,solicitudcambio,tabla,tabladetablas,tablappff,tablapruebappff,tipo,tipo1,tipoprueba,tiposangre,tipotabla,titulo,unidadusuario,vigencia";
			String[] tablas = tablasString.split(",");

			for (String tableName : tablas) {
				String stringFinal = "";
				String sql = "SELECT DISTINCT table_name, column_name," + "case"
						+ "    when domain_name is not null then domain_name"
						+ "    when data_type='character' THEN 'character('||character_maximum_length||')'"
						+ "    when data_type='numeric' THEN 'numeric('||numeric_precision||','||numeric_scale||')'"
						+ "    else data_type" + " end as myType" + " FROM information_schema.columns"
						+ " WHERE table_schema='dbfae01'" + " AND table_name = '" + tableName + "';";
//				System.out.println("Sql: " + sql);
				ResultSet rs = statement.executeQuery(sql);

//				ALTER TABLE dbfae01.condecoracion
//			    ALTER COLUMN recibebonificacion TYPE character varying (1) COLLATE pg_catalog."default";
				String alter = "ALTER TABLE dbfae01.";
				String alterColumn = " ALTER COLUMN ";
				String type = " TYPE bigint USING ";
				String descripcion = "::bigint;";
				while (rs.next()) {
					String nombreColumna = rs.getString(2);
					String tipoColumna = rs.getString(3);
					if (nombreColumna.contains("oid") && !nombreColumna.equals("oid")
							&& tipoColumna.contains("character varying")) {
//						System.out.println("sii: "+tipoColumna);
						stringFinal = alter + tableName + alterColumn + nombreColumna + type + nombreColumna
								+ descripcion;
						System.out.println(stringFinal);
					}
//					stringFinal = stringFinal + "," + rs.getString(2);

				}
			}

		} /*
			 * catch (ClassNotFoundException e) {
			 * System.out.println("PostgreSQL JDBC driver not found."); e.printStackTrace();
			 * }
			 */ catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
