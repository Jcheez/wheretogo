import axios from 'axios';

const IMAGEURL = "http://where-to-go-355603.as.r.appspot.com/api/photos"

export const postImage = (data: FormData):Promise<string> => {
    const promise = axios.post(IMAGEURL, data).then(res => {
        return res.data
    })
    return promise
}