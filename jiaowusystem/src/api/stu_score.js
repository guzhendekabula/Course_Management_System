import MyAxios from "@/request"

//学生成绩查询界面接口
export const getStuScore = async (params) => {
    return await MyAxios.request({
        url:'api/stu/stu_score',
        method:"POST",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}