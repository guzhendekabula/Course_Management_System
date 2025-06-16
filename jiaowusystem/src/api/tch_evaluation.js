import MyAxios from "@/request"

//教师评教查询
export const getEvaluation = async (params) => {
    return await MyAxios.request({
        url:'eva/search',
        method:"POST",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}