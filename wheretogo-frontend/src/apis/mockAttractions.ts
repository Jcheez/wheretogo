import axios from 'axios';
import { attraction } from '../interfaces';

const MOCKATTRACTIONSURL = "http://where-to-go-355603.as.r.appspot.com/api/mock/attractions"

export const getMockAttractions = async ():Promise<attraction[]> => {
    const promise = await axios.get<attraction[]>(MOCKATTRACTIONSURL).then(mock => {
        return mock.data
    })
    return promise
}

export const deleteMockAttraction = async (id:string):Promise<number> => {
    const promise = await axios.delete(MOCKATTRACTIONSURL + "/" + id)
    return promise.status
}