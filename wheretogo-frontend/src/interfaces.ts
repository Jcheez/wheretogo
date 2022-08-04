export interface attractionPayload {
    isMock: boolean
    payload: attraction
}

export interface location {
    address: string
    nearestMRT: string
    latitude: number
    longitude: number
}

export interface attraction {
    name: string
    id: string
    cost: number
    type: string
    photoId ?: string
    locations ?: location[]
    description ?: string
    readMore ?: string
}

export interface GetMockAttractionResponse {
    data: attraction[]
}