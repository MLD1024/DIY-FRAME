package lz.com.http.spring.boot.builder.xml;

import lz.com.http.spring.boot.config.HttpExtensionConfiguration;
import lz.com.http.spring.boot.mapping.HttpMappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈xml  解析器〉
 *
 * @author LZ
 * @create 2019/11/26
 * @since 1.0.0
 */
public class XMLConfigBuilder {


    public static HttpExtensionConfiguration parse(String file, HttpExtensionConfiguration httpExtensionConfiguration) throws DocumentException {
        // step1 获取文件输入流
        InputStream inputStream = XMLConfigBuilder.class.getResourceAsStream(file);
        return parse(inputStream, httpExtensionConfiguration);
    }

    public static HttpExtensionConfiguration parse(InputStream inputStream, HttpExtensionConfiguration httpExtensionConfiguration) throws DocumentException {
        // step2 创建saxReader对象
        SAXReader reader = new SAXReader();
        // step3 通过read方法读取一个文件 转换成Document对象
        Document document = reader.read(inputStream);


        // step4 解析文档树
        //step4.1获取根节点元素对象
        Element node = document.getRootElement();
        //step4.2获取命名空间
        String namespace = node.attribute("namespace").getData().toString();
        //step4.3 获取resultMap子节点列表
        List<Element> resultMaps = node.elements("resultMap");
        // step4.3.1解析resultMap 子元素
        Map<String, Map<String, String>> resulteMap = httpExtensionConfiguration.resulteMap;
        Map<String, HttpMappedStatement> httpMappedStatementMap = httpExtensionConfiguration.httpMappedStatementMap;
        resultMaps.forEach((element) -> {
            String id = element.attribute("id").getData().toString();
            String type = element.attribute("type").getData().toString();
            Map<String, String> tempMap = null;
            if (resulteMap.containsKey(id)) {
                tempMap = resulteMap.get(id);
            } else {
                tempMap = new HashMap<>();
                resulteMap.put(id, tempMap);
            }
            List<Element> result = element.elements("result");
            for (Element item : result) {
                String resultProperty = item.attribute("resultProperty").getData().toString();
                String beanProperty = item.attribute("beanProperty").getData().toString();
                tempMap.put(resultProperty, beanProperty);
            }
        });
        //step4.4 获取url子节点列表
        List<Element> urls = node.elements("url");
        urls.forEach((element) -> {
            String resultMapId = element.attribute("resultMap").getData().toString();
            List<Element> result = element.elements("property");
            for (Element item : result) {
                String name = item.attribute("name").getData().toString();
                String url = item.attribute("value").getData().toString();
                HttpMappedStatement httpMappedStatement = new HttpMappedStatement();
                httpMappedStatement.setUrl(url);
                httpMappedStatement.setResultMap(resulteMap.get(resultMapId));
                httpMappedStatementMap.put(name, httpMappedStatement);
            }
        });
        return httpExtensionConfiguration;
    }

//    //    public Configuration parse() {
////        if (parsed) {
////            throw new BuilderException("Each XMLConfigBuilder can only be used once.");
////        }
////        parsed = true;
////        parseConfiguration(parser.evalNode("/configuration"));
////        return configuration;
////    }
//    public static void main(String[] args) throws XPathExpressionException, IOException {
//        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
////        InputStream resourceAsStream = XMLConfigBuilder.class.getResourceAsStream("./WeixinAdMapper.xml");
////        Resource resource = new ClassPathResource("./WeixinAdMapper.xml", XMLMapperEntityResolver.class.getClassLoader());
////        InputSource inputSource = new InputSource(resource.getInputStream());
////        Document document = xmlConfigBuilder.createDocument(inputSource);
////        xmlConfigBuilder.evalNode(document, "/mapper");
//        InputStream in = XMLConfigBuilder.class.getClassLoader().getResourceAsStream("WeixinAdMapper.xml");
//        xmlConfigBuilder.loadMapperInfo(in);
//    }
//
//    private Document createDocument(InputSource inputSource) {
//        // important: this must only be called AFTER common constructor
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//            factory.setValidating(false);
//            //TODO
//
//            factory.setNamespaceAware(false);
//            factory.setIgnoringComments(true);
//            factory.setIgnoringElementContentWhitespace(false);
//            factory.setCoalescing(false);
//            factory.setExpandEntityReferences(true);
//
//            DocumentBuilder builder = factory.newDocumentBuilder(); // 2> 创建 DocumentBuilder 对象
//            builder.setEntityResolver(new XMLMapperEntityResolver());// 设置实体解析器
//            builder.setErrorHandler(new ErrorHandler() {// 实现都空的
//                @Override
//                public void error(SAXParseException exception) throws SAXException {
//                    throw exception;
//                }
//
//                @Override
//                public void fatalError(SAXParseException exception) throws SAXException {
//                    throw exception;
//                }
//
//                @Override
//                public void warning(SAXParseException exception) throws SAXException {
//                    // NOP
//                }
//            });
//            return builder.parse(inputSource);// 3> 解析 XML 文件
//        } catch (Exception e) {
//            throw new BuilderException("Error creating document instance.  Cause: " + e, e);
//        }
//    }
//
//    public XNode evalNode(Object root, String expression) throws XPathExpressionException {
//        XPathFactory factory = XPathFactory.newInstance();
//        XPath xPath = factory.newXPath();
//        Node node = (Node) xPath.evaluate(expression, root, XPathConstants.NODE);
//        if (node == null) {
//            return null;
//        }
//        return new XNode();
//    }
//
//    private void loadMapperInfo(InputStream file) {
//        // 创建saxReader对象
//        SAXReader reader = new SAXReader();
//        // 通过read方法读取一个文件 转换成Document对象
//        org.dom4j.Document document=null;
//        try {
//            document = reader.read(file);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        //获取根节点元素对象
//        Element node = document.getRootElement();
//        //获取命名空间
//        String namespace = node.attribute("namespace").getData().toString();
//        //获取select子节点列表
//        List<Element> resultMaps = node.elements("resultMap");
//        // 解析resultMap 子元素
//        for (Element element : resultMaps) {//遍历select节点，将信息记录到MappedStatement对象，并登记到configuration对象中
//            List result = element.elements("result");
//
//
////            MappedStatement mappedStatement = new MappedStatement();
//            String id = element.attribute("resultProperty").getData().toString();
//            String resultType = element.attribute("beanProperty").getData().toString();
//            System.out.println(id);
//            System.out.println(resultType);
////            String sql = element.getData().toString();
////            String sourceId = namespace+"."+id;
////            mappedStatement.setSourceId(sourceId);
////            mappedStatement.setResultType(resultType);
////            mappedStatement.setSql(sql);
////            mappedStatement.setNamespace(namespace);
////            conf.getMappedStatements().put(sourceId, mappedStatement);//登记到configuration对象中
//        }
//    }

}
