import { Grid } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { getMockAttractions } from '../apis/mockAttractions'
import AttractionCard from '../components/AttractionCard'
import { attraction, attractionPayload } from '../interfaces'

export default function MockAttractionPage() {

    const [mockAttractions, setMockAttractions] = useState<attraction[]>([])

    useEffect(() => {
        getMockAttractions().then(mocks => {
            console.log(mocks)
            setMockAttractions(mocks)
        })
    }, [])

    const renderMockAttractions = (mockAttractions: attraction[]) => {
        const isMock: boolean = true
        const res: React.ReactElement[] = []
        for (let mockAttraction of mockAttractions) {
            res.push(
                <Grid item xs={6} md={4}>
                    <AttractionCard isMock={isMock} payload={mockAttraction} key={mockAttraction.id}></AttractionCard>
                </Grid>
            )
        }
        return res
    }

    return (
        <div>
            <br/>
            <Grid container spacing={3}>
                {renderMockAttractions(mockAttractions)}
            </Grid>
        </div>
    )
}
