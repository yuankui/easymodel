package io.github.yuankui.easymodel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.yuankui.easymodel.request.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class ServerImpl implements Server {
    private static final int maxBodyLength = 5 * 1024 * 1024;
    private final Map<String, Table> tableMap;

    private Service service;

    public ServerImpl(Service service) {
        this.service = service;
        this.tableMap = service.tables()
                .stream()
                .collect(Collectors.toMap(
                        Table::name,
                        t -> t
                ));
    }

    @Override
    public void request(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder sb = new StringBuilder(1000);
            BufferedReader reader = request.getReader();
        
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\n");
                if (sb.length() > maxBodyLength) {
                    throw new RuntimeException("body to long, larger than 5MB");
                }
            }

            JSONObject jsonObject = JSON.parseObject(sb.toString());

            Request req = jsonObject.toJavaObject(Request.class);

            Table table = tableMap.get(req.getTable());
            
            if (table == null) {
                throw new RuntimeException("invalid table:" + req.getTable());
            }

            if (req.getType().equals("insert")) {
                InsertParam insertParam = req.getParam().toJavaObject(InsertParam.class);
                table.insert(insertParam.getObj());
                response.getWriter().write("success");

            } else if (req.getType().equals("update")) {
                UpdateParam updateParam = req.getParam().toJavaObject(UpdateParam.class);
                table.update(updateParam.getId(), updateParam.getObj());
                response.getWriter().write("success");

            } else if (req.getType().equals("delete")) {
                DeleteParam param = req.getParam().toJavaObject(DeleteParam.class);
                table.delete(param.getId());
                response.getWriter().write("success");

            } else if (req.getType().equals("query")) {
                QueryParam param = req.getParam().toJavaObject(QueryParam.class);
                Queryable query = table.query();
                response.getWriter().write(JSON.toJSONString(query.list()));
            } else{
                throw new RuntimeException("invalid request type");
            }
            
            response.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
