<!DOCTYPE html>
<style>
    .text {
        margin-top:18px;
        font-size: 13px;
        font-family: "Microsoft YaHei";
    }
    table.gridtable {
                text-align: center;
                font-family: Microsoft YaHei;
                font-size:13px;
                color:#333333;
                border-width: 1px;
                border-color: #666666;
                border-collapse: collapse;
    }
    table.gridtable th {
                width: 100px;
                height: 50px;
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #666666;
                background-color: #dedede;
    }
    table.gridtable td {
                width: 100px;
                height: 50px;
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #666666;
                background-color: #ffffff;
    }
</style>
<html>
    <body>
        <#setting number_format="#">
        <div class="text">
            Dear Claire & Yingman,
        </div>
        <div class="text">${mail_text}</div>
        <div style="margin-top: 15px">
            <table class="gridtable">
                <#list statistics as exmap>
                    <#if exmap_index = 0>
                       <tr>
                          <#list exmap?keys as exitemKey>
                              <th>${exitemKey}</th>
                          </#list>
                       </tr>
                    </#if>
                    <tr style="color: black;">
                        <#list exmap?keys as exitemKey>
                            <td>${exmap[exitemKey]!''}</td>
                        </#list>
                    </tr>
                </#list>
            </table>
        </div>
        <div class="text">
            <div class="text">B.R</div>
            <div class="text">如有问题，请与GA OP专员郑增微联系，联系方式：zzheng3@its.jnj.com 电话：18500289813。此邮箱为公共邮箱，请勿直接回复。谢谢！</div>
            <div class="text" style="font-weight: bold; ">JJMC GA Operation Team</div>
        </div>
    </body>
</html>