


	package com.wings4.core;
  
	import java.awt.Color;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    import ar.com.fdvs.dj.core.DynamicJasperHelper;
    import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
    import ar.com.fdvs.dj.domain.*;
    import com.wings4.dao.MaterialDao;
    import com.wings4.model.Product;
    import com.wings4.model.TestProduct;
    import net.sf.jasperreports.engine.JRDataSource;
    import net.sf.jasperreports.engine.JasperFillManager;
    import net.sf.jasperreports.engine.JasperPrint;
    import net.sf.jasperreports.engine.JasperReport;
    import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
    import net.sf.jasperreports.engine.util.JRLoader;
    import net.sf.jasperreports.view.JasperDesignViewer;
	import net.sf.jasperreports.view.JasperViewer;
    import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
	import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
	import ar.com.fdvs.dj.domain.builders.GroupBuilder;
	import ar.com.fdvs.dj.domain.constants.Border;
	import ar.com.fdvs.dj.domain.constants.Font;
	import ar.com.fdvs.dj.domain.constants.GroupLayout;
	import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
	import ar.com.fdvs.dj.domain.constants.Transparency;
	import ar.com.fdvs.dj.domain.constants.VerticalAlign;
	import ar.com.fdvs.dj.domain.entities.DJGroup;
	import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
	import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
  
	public class GroupsReportTest  {
	
		public DynamicReport buildReport() throws Exception {
 
			Style detailStyle = new Style("detail");
  
			Style headerStyle = new Style("header");
			headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
			headerStyle.setBorderBottom(Border.PEN_1_POINT());
			headerStyle.setBackgroundColor(Color.gray);
			headerStyle.setTextColor(Color.white);
			headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
			headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
			headerStyle.setTransparency(Transparency.OPAQUE);

			Style headerVariables = new Style("headerVariables");
			headerVariables.setFont(Font.ARIAL_BIG_BOLD);
			headerVariables.setBorderBottom(Border.THIN());
			headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
			headerVariables.setVerticalAlign(VerticalAlign.TOP);
			headerVariables.setStretchWithOverflow(true);
	
			Style groupVariables = new Style("groupVariables");
			groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
			groupVariables.setTextColor(Color.BLUE);
			groupVariables.setBorderBottom(Border.THIN());
			groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
			groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);
  
			Style titleStyle = new Style("titleStyle");
			titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
            titleStyle.setBorderBottom(Border.THIN());
			Style importeStyle = new Style();
			importeStyle.setHorizontalAlign(HorizontalAlign.LEFT);
			Style oddRowStyle = new Style();
			oddRowStyle.setBorder(Border.NO_BORDER());
			oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
			oddRowStyle.setTransparency(Transparency.OPAQUE);

	
			DynamicReportBuilder drb = new DynamicReportBuilder();
			Integer margin = new Integer(20);
			drb
            .addFirstPageImageBanner("com/wings4/resource/category.png", new Integer(50), new Integer(50), ImageBanner.ALIGN_LEFT)
                    .addImageBanner("com/wings4/resource/customer.png", new Integer(30), new Integer(30), ImageBanner.ALIGN_RIGHT)
				.setTitleStyle(titleStyle)
				.setTitle("Nafisa Enterprise")					//defines the title of the report
				.setSubtitle("Textile Dyestuff & Auxiliary chemical importers, Indentor & General Suppliers")
                .setSubtitleStyle(importeStyle)
                //.setTemplateFile()
				.setDetailHeight(new Integer(15)).setLeftMargin(margin)
				.setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
				.setPrintBackgroundOnOddRows(true)
				.setGrandTotalLegend("Grand Total")
				.setGrandTotalLegendStyle(headerVariables)
				.setOddRowBackgroundStyle(oddRowStyle);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject("com/report/asda.jasper");
            Map parameters = new HashMap();
            JRDataSource productDataSource = new JRBeanCollectionDataSource(new ArrayList());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, productDataSource);
 
			AbstractColumn columnState = ColumnBuilder.getNew()
 				.setColumnProperty("state", String.class.getName()).setTitle(
 						"State").setWidth(new Integer(85))
 				.setStyle(titleStyle).setHeaderStyle(titleStyle).build();
 
			AbstractColumn columnBranch = ColumnBuilder.getNew()
					.setColumnProperty("branch", String.class.getName()).setTitle(
 						"Branch").setWidth(new Integer(85)).setStyle(
 						detailStyle).setHeaderStyle(headerStyle).build();
 
			AbstractColumn columnaProductLine = ColumnBuilder.getNew()
					.setColumnProperty("productLine", String.class.getName())
					.setTitle("Product Line").setWidth(new Integer(85)).setStyle(
							detailStyle).setHeaderStyle(headerStyle).build();
 
			AbstractColumn columnaItem = ColumnBuilder.getNew()
					.setColumnProperty("item", String.class.getName()).setTitle(
							"Item").setWidth(new Integer(85)).setStyle(detailStyle)
					.setHeaderStyle(headerStyle).build();

			AbstractColumn columnCode = ColumnBuilder.getNew()
					.setColumnProperty("id", Long.class.getName()).setTitle("ID")
					.setWidth(new Integer(40)).setStyle(importeStyle)
					.setHeaderStyle(headerStyle).build();

			AbstractColumn columnaQuantity = ColumnBuilder.getNew()
					.setColumnProperty("quantity", Long.class.getName()).setTitle(
							"Quantity").setWidth(new Integer(25)).setStyle(
							importeStyle).setHeaderStyle(headerStyle).build();
	
			AbstractColumn columnAmount = ColumnBuilder.getNew()
					.setColumnProperty("amount", Float.class.getName()).setTitle(
							"Amount").setWidth(new Integer(100))
					.setPattern("$ 0.00").setStyle(importeStyle).setHeaderStyle(
							headerStyle).build();
 
			drb.addGlobalHeaderVariable(columnAmount, DJCalculation.SUM,headerVariables);
			drb.addGlobalHeaderVariable(columnaQuantity, DJCalculation.SUM,headerVariables);
			drb.addGlobalFooterVariable(columnAmount, DJCalculation.SUM,headerVariables);
			drb.addGlobalFooterVariable(columnaQuantity, DJCalculation.SUM,headerVariables);
			drb.setGlobalHeaderVariableHeight(new Integer(25));
			drb.setGlobalFooterVariableHeight(new Integer(25));
	
			GroupBuilder gb1 = new GroupBuilder();
	
	//		 define the criteria column to group by (columnState)
			DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnState)
					.addFooterVariable(columnaQuantity,DJCalculation.SUM,groupVariables) // idem for the columnaQuantity column
	//				.addFooterVariable(columnAmount,DJCalculation.SUM,groupVariables) // tell the group place a variable footer of the column "columnAmount" with the SUM of allvalues of the columnAmount in this group.
					.addHeaderVariable(columnaQuantity,DJCalculation.SUM,groupVariables) // idem for the columnaQuantity column
					.addHeaderVariable(columnAmount,DJCalculation.SUM,groupVariables) // tell the group place a variable footer of the column "columnAmount" with the SUM of allvalues of the columnAmount in this group.
					.setGroupLayout(GroupLayout.VALUE_IN_HEADER) // tells the group how to be shown, there are manyposibilities, see the GroupLayout for more.
					.setFooterVariablesHeight(new Integer(20))
					.setFooterHeight(new Integer(50),true)
					.setHeaderVariablesHeight(new Integer(35))
					.build();
 
			GroupBuilder gb2 = new GroupBuilder(); // Create another group (using another column as criteria)
			DJGroup g2 = gb2.setCriteriaColumn((PropertyColumn) columnBranch) // and we add the same operations for the columnAmount and
					.addFooterVariable(columnAmount,
							DJCalculation.SUM) // columnaQuantity columns
					.addFooterVariable(columnaQuantity,	DJCalculation.SUM)
					.build();
	
			drb.addColumn(columnState);
			drb.addColumn(columnBranch);
			drb.addColumn(columnaProductLine);
			drb.addColumn(columnaItem);
			drb.addColumn(columnCode);
			drb.addColumn(columnaQuantity);
			drb.addColumn(columnAmount);
 
			drb.addGroup(g1); // add group g1
	//		drb.addGroup(g2); // add group g2
 
			drb.setUseFullPageWidth(true);
			drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
	
			DynamicReport dr = drb.build();
			return dr;
		}

		public static void main(String[] args) throws Exception {
			GroupsReportTest test = new GroupsReportTest();

            List<TestProduct> products = new ArrayList<TestProduct>();
            TestProduct testProduct = new TestProduct();
            testProduct.setState("Upta");
            testProduct.setQuantity(10L);
            testProduct.setProductLine("Direct");
            testProduct.setItem("Direct");
            testProduct.setBranch("gas pala");
            testProduct.setAmount(200);
            testProduct.setId(3645L);

            products.add(testProduct);

            JRDataSource productDataSource = new JRBeanCollectionDataSource(products);
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(test.buildReport(),
                    new ClassicLayoutManager(), productDataSource);
            JasperViewer.viewReport(jasperPrint);
		}
}